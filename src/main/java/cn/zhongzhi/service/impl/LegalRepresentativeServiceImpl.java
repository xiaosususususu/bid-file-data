package cn.zhongzhi.service.impl;


import cn.hutool.json.JSONUtil;
import cn.zhongzhi.domain.LegalRepresentative;
import cn.zhongzhi.domain.LegalRepresentativeBack;
import cn.zhongzhi.mapper.LegalRepresentativeBackMapper;
import cn.zhongzhi.mapper.LegalRepresentativeMapper;
import cn.zhongzhi.service.IStructuralDataPublicInterface;
import cn.zhongzhi.service.LegalRepresentativeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void dataOperation(String tableName, String jsonData, Map<String,String> relationColumnData) {
        if ("legal_representative".equals(tableName)){
            LegalRepresentative legalRepresentative = JSONUtil.toBean(jsonData, LegalRepresentative.class);
            legalRepresentative.setLegalRepresentativeBackId(relationColumnData.getOrDefault("legalRepresentativeBackId",""));
            legalRepresentative.setFileId(relationColumnData.getOrDefault("fileId", ""));
            legalRepresentative.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
            int count = legalRepresentativeMapper.insert(legalRepresentative);
            if (count<=0){
                log.error("插入法人代表正面表失败：tableName{}，jsonData：{}",tableName,jsonData);
                throw new RuntimeException("插入法人代表表失败");
            }
            relationColumnData.put("legalRepresentativeId",legalRepresentative.getId());
        }else if ("legal_representative_back".equals(tableName)){
            LegalRepresentativeBack legalRepresentativeBack = JSONUtil.toBean(jsonData, LegalRepresentativeBack.class);
            legalRepresentativeBack.setLegalRepresentativeId(relationColumnData.getOrDefault("legalRepresentativeId",""));
            legalRepresentativeBack.setFileId(relationColumnData.getOrDefault("fileId", ""));
            legalRepresentativeBack.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
            int count = legalRepresentativeBackMapper.insert(legalRepresentativeBack);
            if (count<=0){
                log.error("插入法人代表背面表失败：tableName{}，jsonData：{}",tableName,jsonData);
                throw new RuntimeException("插入法人代表背面表失败");
            }
            relationColumnData.put("legalRepresentativeBackId",legalRepresentativeBack.getId());
        }
    }

}