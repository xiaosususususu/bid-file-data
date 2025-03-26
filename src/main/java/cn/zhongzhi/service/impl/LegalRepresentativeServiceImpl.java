package cn.zhongzhi.service.impl;


import cn.hutool.core.util.ObjUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.zhongzhi.domain.LegalRepresentative;
import cn.zhongzhi.domain.LegalRepresentativeBack;
import cn.zhongzhi.mapper.LegalRepresentativeBackMapper;
import cn.zhongzhi.mapper.LegalRepresentativeMapper;
import cn.zhongzhi.service.FileSystemService;
import cn.zhongzhi.service.IStructuralDataPublicInterface;
import cn.zhongzhi.service.LegalRepresentativeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author suchengbo
 */

@Service
@Slf4j
public class LegalRepresentativeServiceImpl extends ServiceImpl<LegalRepresentativeMapper, LegalRepresentative> implements LegalRepresentativeService, IStructuralDataPublicInterface {
    @Resource
    private LegalRepresentativeMapper legalRepresentativeMapper;
    @Resource
    private LegalRepresentativeBackMapper legalRepresentativeBackMapper;
    @Resource
    private FileSystemService fileSystemService;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void dataOperation(String tableName, String jsonData, Map<String,String> relationColumnData) {
        if ("legal_representative".equals(tableName)){
            JSONObject legalRepresentativeJsonObject = JSONUtil.parseObj(jsonData);
            LegalRepresentative legalRepresentative = legalRepresentativeJsonObject.getBean("id-card-front", LegalRepresentative.class);
            if (ObjUtil.isEmpty(legalRepresentative)) {
                legalRepresentative = JSONUtil.toBean(jsonData, LegalRepresentative.class);
            }

            legalRepresentative.setLegalRepresentativeBackId(relationColumnData.getOrDefault("legalRepresentativeBackId",""));
            legalRepresentative.setFileId(relationColumnData.getOrDefault("fileId", ""));
            legalRepresentative.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
            int count = legalRepresentativeMapper.insert(legalRepresentative);
            if (count<=0){
                log.error("插入法人代表正面表失败：tableName{}，jsonData：{}",tableName,jsonData);
                throw new RuntimeException("插入法人代表表失败");
            }
            if (ObjUtil.isNotEmpty(relationColumnData.get("legalRepresentativeBackId"))) {
                legalRepresentativeBackMapper.updateById(LegalRepresentativeBack.builder().id(relationColumnData.get("legalRepresentativeBackId")).legalRepresentativeId(legalRepresentative.getId()).build());
            }
            relationColumnData.put("legalRepresentativeId",legalRepresentative.getId());
        }else if ("legal_representative_back".equals(tableName)){
            JSONObject representativeBackJsonObject = JSONUtil.parseObj(jsonData);
            LegalRepresentativeBack legalRepresentativeBack = representativeBackJsonObject.getBean("back_message", LegalRepresentativeBack.class);
            if (ObjUtil.isEmpty(legalRepresentativeBack)) {
                legalRepresentativeBack = JSONUtil.toBean(jsonData, LegalRepresentativeBack.class);
            }
            legalRepresentativeBack.setLegalRepresentativeId(relationColumnData.getOrDefault("legalRepresentativeId",""));
            legalRepresentativeBack.setFileId(relationColumnData.getOrDefault("fileId", ""));
            legalRepresentativeBack.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
            int count = legalRepresentativeBackMapper.insert(legalRepresentativeBack);
            if (count<=0){
                log.error("插入法人代表背面表失败：tableName{}，jsonData：{}",tableName,jsonData);
                throw new RuntimeException("插入法人代表背面表失败");
            }
            if (ObjUtil.isNotEmpty(relationColumnData.get("legalRepresentativeId"))) {
                legalRepresentativeMapper.updateById(LegalRepresentative.builder().id(relationColumnData.get("legalRepresentativeId")).legalRepresentativeBackId(legalRepresentativeBack.getId()).build());
            }
            relationColumnData.put("legalRepresentativeBackId",legalRepresentativeBack.getId());
        }
    }

    @Override
    public Map<String, Object> organizeIdentityData(String bidderId) {
        //1.查询正面信息
        LegalRepresentative legalRepresentative = legalRepresentativeMapper.selectById(bidderId);
        if (ObjUtil.isEmpty(legalRepresentative)) {
            return null;
        }
        String frontFileId = legalRepresentative.getFileId();
        String frontFileFullPath = fileSystemService.fileIdExchangeFullPath(frontFileId);
        //2.查询对应的背面
        LegalRepresentativeBack legalRepresentativeBack = legalRepresentativeBackMapper.selectById(legalRepresentative.getLegalRepresentativeBackId());
        String backFileId = legalRepresentativeBack.getFileId();
        String backFileFullPath = fileSystemService.fileIdExchangeFullPath(backFileId);
        legalRepresentative.setFileId(frontFileFullPath);
        legalRepresentativeBack.setFileId(backFileFullPath);
        //3.组合数据
        Map<String, Object> resultData = new LinkedHashMap<>();
        resultData.put("idCardFrontInfo", legalRepresentative);
        resultData.put("idCardBackInfo", legalRepresentativeBack);
        return resultData;
    }
}