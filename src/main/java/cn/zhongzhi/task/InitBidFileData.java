package cn.zhongzhi.task;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.zhongzhi.config.properties.KeywordConfig;
import cn.zhongzhi.enums.OssTypeEnum;
import cn.zhongzhi.factory.OssServiceFactory;
import cn.zhongzhi.factory.StructuralDataOperationExecute;
import cn.zhongzhi.oss.OssService;
import cn.zhongzhi.util.component.TextMatchingService;
import cn.zhongzhi.util.http.HttpUtils;
import io.minio.Result;
import io.minio.messages.Item;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author suchengbo
 * @Date 2025/3/21 14:18
 */
@Component
public class InitBidFileData {

    @Resource
    private OssServiceFactory ossServiceFactory;
    @Resource
    private TextMatchingService textMatchingService;
    @Resource
    private StructuralDataOperationExecute structuralDataOperationExecute;
    @Value("${ai.endpoint}")
    private String url;
    @Value("${ai.api-key}")
    private String apiKey;

    private static final List<String> IMAGE_SUFFIXES = List.of(".jpg", ".jpeg", ".png", ".gif", ".bmp", ".tiff");
    public void getAllFileData() throws Exception{
        OssService ossService = ossServiceFactory.getOssService(OssTypeEnum.MINIO);
        Iterable<Result<Item>> results = ossService.scanImageAndTxtPaths();
        //用于保存映射字段的值
        Map<String, String> relationColumnData=new HashMap<>();
        //构建一个List用于保存file_set_schema_name-file_item_schema_name  List
        List<String> schemaNameList=new ArrayList<>();
        //构建一个Set用于判断file_item_schema是否已经解析过一次了
        Set<String> schemaNameSet=new HashSet<>();
        for (Result<Item> result : results) {
            Item item = result.get();
            String fullPath = item.objectName();
            if (isTxt(fullPath)) {
                //430000/G354黄兴大道至机场中轴大道(湘府东路东延城际快速干道)施工（机场大道至机场中轴大道段）（JJSG-02标）/投标文件/中铁大桥局集团有限公司（牵头方）中铁大桥勘测设计院集团有限公司（成员方）/投标文件格式（第一个信封商务及技术文件）/资格审查资料（适用于未进行资格预审）/资格审查资料（适用于未进行资格预审）_page70_image1.txt
                //行政区代码/投标项目/投标文件/投标人/
                //采用网络流读取txt文本内容
                String ocrContent = ossService.readTxtContent(fullPath);
                //去除ocrContent中所有的换行符和空格
                String content = ocrContent.replaceAll("\\s+", "");
                //使用DFA算法进行匹配
                KeywordConfig.ResultInnerGroup resultInnerGroup = textMatchingService.matchAndGetSchema(fullPath, content);
                if (ObjUtil.isNotEmpty(resultInnerGroup)){
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
                    if (!isSuccess){
                        relationColumnData.clear();
                    }
                    //证明已经跳到另外一个file_set_schema了
                    if (!contains){
                        schemaNameSet.clear();
                        schemaNameList.clear();
                        relationColumnData.clear();
                    }
                    //组合fileItemSchemaList中的file_item_schema_name，与fileSetSchemaName进行组合
                    if (CollUtil.isEmpty(schemaNameList)){
                        for (KeywordConfig.InnerGroup innerGroupFileItemSchema : fileItemSchemaList) {
                            String schemaName = innerGroupFileItemSchema.getFileItemSchemaName();
                            schemaNameList.add(fileSetSchemaName+"-"+schemaName);
                        }
                    }
                    KeywordConfig.InnerGroup sureInnerGroup = fileItemSchemaList.stream().filter(innerGroup -> innerGroup.getFileItemSchemaName().equals(fileItemSchemaName)).findFirst().orElse(null);
                    //构造参数
                    Map<String,Object> paramsLinkedHashMap=new LinkedHashMap<>();
                    paramsLinkedHashMap.put("file_item_schema_name",fileItemSchemaName);
                    paramsLinkedHashMap.put("file-item-schema",sureInnerGroup.getSchema());
                    paramsLinkedHashMap.put("file_ocr_result",ocrContent);
                    //发起请求
                    String aiResult = HttpUtils.sendPostRequest(url, apiKey, paramsLinkedHashMap);
                    if (ObjUtil.isNotEmpty(aiResult)){
                        structuralDataOperationExecute.execute(sureInnerGroup.getTableName(), aiResult,relationColumnData);
                    }
                }
            }
        }

    }


    private boolean isImage(String objectName) {
        return IMAGE_SUFFIXES.stream()
                .anyMatch(suffix -> objectName.toLowerCase().endsWith(suffix));
    }

    private boolean isTxt(String objectName) {
        return objectName.toLowerCase().endsWith(".txt");
    }
}
