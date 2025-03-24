package cn.zhongzhi.task;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.zhongzhi.config.properties.KeywordConfig;
import cn.zhongzhi.domain.*;
import cn.zhongzhi.enums.OssTypeEnum;
import cn.zhongzhi.factory.OssServiceFactory;
import cn.zhongzhi.factory.StructuralDataOperationExecute;
import cn.zhongzhi.oss.OssService;
import cn.zhongzhi.service.*;
import cn.zhongzhi.util.component.TextMatchingService;
import cn.zhongzhi.util.http.HttpUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author suchengbo
 * @Date 2025/3/21 14:18
 */
@Slf4j
@Component
public class InitBidFileData {

    @Resource
    private OssServiceFactory ossServiceFactory;
    @Resource
    private OperationFileDataService operationFileDataService;
    @Resource
    private BidderService bidderService;
    @Resource
    private TenderProjectService tenderProjectService;
    @Resource
    private TenderProjectSectionService tenderProjectSectionService;
    @Resource
    private BidderSectionRelation bidderSectionRelation;


    private static final List<String> IMAGE_SUFFIXES = List.of(".jpg", ".jpeg", ".png", ".gif", ".bmp", ".tiff");
    @Autowired
    private BidderSectionRelationService bidderSectionRelationService;


    public void getAllFileData() {
        OssService ossService = ossServiceFactory.getOssService(OssTypeEnum.MINIO);
        Iterable<Result<Item>> results = ossService.scanImageAndTxtPaths();
        //用于保存映射字段的值
        Map<String, String> relationColumnData=new HashMap<>();
        //构建一个List用于保存file_set_schema_name-file_item_schema_name  List
        List<String> schemaNameList=new ArrayList<>();
        //构建一个Set用于判断file_item_schema是否已经解析过一次了
        Set<String> schemaNameSet=new HashSet<>();
        for (Result<Item> result : results) {
            try {
                operationFileDataService.operationFileData(result, relationColumnData, ossService, schemaNameSet, schemaNameList);
            } catch (Exception e) {
                log.error("文件数据结构化失败", e);
            }
        }

    }

    // 新增类 OperationFileDataService

    @Component
    public class OperationFileDataService {

        @Resource
        private TextMatchingService textMatchingService;
        @Resource
        private StructuralDataOperationExecute structuralDataOperationExecute;
        @Resource
        private FileSystemService fileSystemService;

        @Value("${ai.endpoint}")
        private String url;
        @Value("${ai.api-key}")
        private String apiKey;

        @Transactional(propagation = Propagation.REQUIRED)
        public void operationFileData(Result<Item> result, Map<String, String> relationColumnData, OssService ossService, Set<String> schemaNameSet, List<String> schemaNameList) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
            Item item = result.get();
            String fullPath = item.objectName();
            if (isTxt(fullPath)) {
                //判断fullPath是否已经存过了，如果已经存过了，那么直接return；
                //TODO 效率待提升
                boolean exists = fileSystemService.exists(new QueryWrapper<FileSystem>().eq("path", fullPath));
                if (exists) {
                    return;
                }
                //430000/G354黄兴大道至机场中轴大道(湘府东路东延城际快速干道)施工（机场大道至机场中轴大道段）（JJSG-02标）/投标文件/中铁大桥局集团有限公司（牵头方）中铁大桥勘测设计院集团有限公司（成员方）/投标文件格式（第一个信封商务及技术文件）/资格审查资料（适用于未进行资格预审）/资格审查资料（适用于未进行资格预审）_page70_image1.txt
                //行政区代码/投标项目/投标文件/投标人/
                //采用网络流读取txt文本内容
                String ocrContent = ossService.readTxtContent(fullPath);
                //去除ocrContent中所有的换行符和空格
                String content = ocrContent.replaceAll("\\s+", "");
                //使用DFA算法进行匹配
                KeywordConfig.ResultInnerGroup resultInnerGroup = textMatchingService.matchAndGetSchema(fullPath, content);
                if (ObjUtil.isNotEmpty(resultInnerGroup)) {
                    //获取匹配到的file_set_schema_name
                    String fileSetSchemaName = resultInnerGroup.getFileSetSchemaName();
                    //获取匹配到的file_item_name
                    String fileItemSchemaName = resultInnerGroup.getFileItemSchemaName();
                    //获取匹配到的file_item_schema
                    List<KeywordConfig.InnerGroup> fileItemSchemaList = resultInnerGroup.getFileItemSchema();
                    //组合file_set_schema_name-file_item_schema_name
                    String thisMatchSchemaName = fileSetSchemaName + "-" + fileItemSchemaName;
                    boolean isSuccess = schemaNameSet.add(thisMatchSchemaName);
                    boolean contains = schemaNameList.contains(thisMatchSchemaName);
                    if (!isSuccess) {
                        relationColumnData.clear();
                    }
                    //证明已经跳到另外一个file_set_schema了
                    if (!contains) {
                        schemaNameSet.clear();
                        schemaNameList.clear();
                        relationColumnData.clear();
                    }
                    //组合fileItemSchemaList中的file_item_schema_name，与fileSetSchemaName进行组合
                    if (CollUtil.isEmpty(schemaNameList)) {
                        for (KeywordConfig.InnerGroup innerGroupFileItemSchema : fileItemSchemaList) {
                            String schemaName = innerGroupFileItemSchema.getFileItemSchemaName();
                            schemaNameList.add(fileSetSchemaName + "-" + schemaName);
                        }
                    }
                    //存bidder信息
                    saveBidderInfo(fullPath, relationColumnData);
                    //存此路径至file_system表
                    String fileSystemFileId = fileSystemService.saveFileOrFolder(fullPath);
                    if (StrUtil.isBlank(fileSystemFileId)) {
                        throw new RuntimeException("file_system表插入失败");
                    }
                    relationColumnData.put("fileId", fileSystemFileId);
                    KeywordConfig.InnerGroup sureInnerGroup = fileItemSchemaList.stream().filter(innerGroup -> innerGroup.getFileItemSchemaName().equals(fileItemSchemaName)).findFirst().orElse(null);
                    //构造参数
                    Map<String, Object> paramsLinkedHashMap = new LinkedHashMap<>();
                    paramsLinkedHashMap.put("file_item_schema_name", fileItemSchemaName);
                    paramsLinkedHashMap.put("file-item-schema", sureInnerGroup.getSchema());
                    paramsLinkedHashMap.put("file_ocr_result", ocrContent);
                    //发起请求
                    String aiResult = HttpUtils.sendPostRequest(url, apiKey, paramsLinkedHashMap);
                    if (ObjUtil.isNotEmpty(aiResult)) {
                        structuralDataOperationExecute.execute(sureInnerGroup.getTableName(), aiResult, relationColumnData);
                    }
                }
            }
        }

    }

    private boolean isTxt(String objectName) {
        return objectName.toLowerCase().endsWith(".txt");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveBidderInfo(String fullPath, Map<String, String> relationColumnData) {
        String[] dirNamePaths = fullPath.split(StrPool.SLASH);
        //行政区代码
        String regionCode = dirNamePaths[0];
        //投标项目
        String tendProjectName = dirNamePaths[1];
        //投标人名称
        String bidderName = dirNamePaths[3];
        //构造投标人信息
        Bidder bidder = new Bidder();
        bidder.setSupplierName(bidderName);
        //先判断是否已经有了，有了就不新增
        Bidder existBidder = bidderService.getOne(new QueryWrapper<Bidder>().eq("supplier_name", bidderName));
        if (ObjUtil.isEmpty(existBidder)) {
            bidderService.save(bidder);
        } else {
            bidder = existBidder;
        }
        relationColumnData.put("bidderId", bidder.getId());
        //判断
        //招投标项目信息
        TenderProject tenderProject = new TenderProject();
        tenderProject.setTenderProjectName(tendProjectName);
        tenderProject.setRegionCode(regionCode);
        TenderProject existTenderProject = tenderProjectService.getOne(new QueryWrapper<TenderProject>().eq("tender_project_name", tendProjectName).eq("region_code", regionCode));
        if (ObjUtil.isEmpty(existTenderProject)) {
            //保存一个招投标项目信息
            tenderProjectService.save(tenderProject);
        } else {
            tenderProject = existTenderProject;
        }
        //构造标段信息
        TenderProjectSection tenderProjectSection = new TenderProjectSection();
        tenderProjectSection.setProjectId(tenderProject.getId());
        TenderProjectSection existTenderProjectSection = tenderProjectSectionService.getOne(new QueryWrapper<TenderProjectSection>().eq("project_id", tenderProject.getId()));
        if (ObjUtil.isEmpty(existTenderProjectSection)) {
            //保存一个招投标项目信息
            tenderProjectSectionService.save(tenderProjectSection);
        } else {
            tenderProjectSection = existTenderProjectSection;
        }
        //构造标段和投标人的关联信息
        BidderSectionRelation bidderSectionRelation = new BidderSectionRelation();
        bidderSectionRelation.setBidderId(bidder.getId());
        bidderSectionRelation.setSectionId(tenderProjectSection.getId());
        BidderSectionRelation existBidderSectionRelation = bidderSectionRelationService.getOne(new QueryWrapper<BidderSectionRelation>().eq("bidder_id", bidder.getId()).eq("section_id", tenderProjectSection.getId()));
        if (ObjUtil.isEmpty(existBidderSectionRelation)) {
            bidderSectionRelationService.save(bidderSectionRelation);
        }
    }
}
