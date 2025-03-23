package cn.zhongzhi.service.impl;


import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import cn.zhongzhi.domain.LegalRepresentative;
import cn.zhongzhi.domain.LegalRepresentativeBack;
import cn.zhongzhi.mapper.LegalRepresentativeBackMapper;
import cn.zhongzhi.mapper.LegalRepresentativeMapper;
import cn.zhongzhi.service.ILegalRepresentativeService;
import cn.zhongzhi.service.StructuralDataPublicInterface;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author suchengbo
 */
@Service
@Slf4j
public class LegalRepresentativeServiceImpl extends ServiceImpl<LegalRepresentativeMapper, LegalRepresentative> implements ILegalRepresentativeService, StructuralDataPublicInterface {
    @Resource
    private LegalRepresentativeMapper legalRepresentativeMapper;
    @Resource
    private LegalRepresentativeBackMapper legalRepresentativeBackMapper;
    @Override
    public void dataOperation(String tableName, String jsonData, Map<String,String> relationColumnData) {
        if ("legal_representative".equals(tableName)){
            LegalRepresentative legalRepresentative = JSONUtil.toBean(jsonData, LegalRepresentative.class);
            legalRepresentative.setLegalRepresentativeBackId(relationColumnData.getOrDefault("legalRepresentativeBackId",""));
            int count = legalRepresentativeMapper.insert(legalRepresentative);
            if (count<=0){
                log.error("插入法人代表正面表失败：tableName{}，jsonData：{}",tableName,jsonData);
                throw new RuntimeException("插入法人代表表失败");
            }
            relationColumnData.put("legalRepresentativeId",legalRepresentative.getId());
        }else if ("legal_representative_back".equals(tableName)){
            LegalRepresentativeBack legalRepresentativeBack = JSONUtil.toBean(jsonData, LegalRepresentativeBack.class);
            legalRepresentativeBack.setLegalRepresentativeId(relationColumnData.getOrDefault("legalRepresentativeId",""));
            int count = legalRepresentativeBackMapper.insert(legalRepresentativeBack);
            if (count<=0){
                log.error("插入法人代表背面表失败：tableName{}，jsonData：{}",tableName,jsonData);
                throw new RuntimeException("插入法人代表背面表失败");
            }
            relationColumnData.put("legalRepresentativeBackId",legalRepresentativeBack.getId());
        }
    }

}