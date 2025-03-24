package cn.zhongzhi.service.impl;

import cn.hutool.json.JSONUtil;
import cn.zhongzhi.domain.SeniorEngineerCertificateFirst;
import cn.zhongzhi.mapper.SeniorEngineerCertificateFirstMapper;
import cn.zhongzhi.service.IStructuralDataPublicInterface;
import cn.zhongzhi.service.SeniorEngineerCertificateFirstService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 高级工程师证书一级相关信息服务实现类
 */
@Service
@Slf4j
public class SeniorEngineerCertificateFirstServiceImpl extends ServiceImpl<SeniorEngineerCertificateFirstMapper, SeniorEngineerCertificateFirst> implements SeniorEngineerCertificateFirstService, IStructuralDataPublicInterface {
    @Override
    public void dataOperation(String tableName, String jsonData, Map<String, String> relationColumnData) {
        SeniorEngineerCertificateFirst seniorEngineerCertificateFirst = JSONUtil.toBean(jsonData, SeniorEngineerCertificateFirst.class);
        seniorEngineerCertificateFirst.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
        seniorEngineerCertificateFirst.setFileId(relationColumnData.getOrDefault("fileId", ""));
        seniorEngineerCertificateFirst.setSeniorEngineerCertificateLastId(relationColumnData.getOrDefault("seniorEngineerCertificateLastId", ""));
        if (!this.save(seniorEngineerCertificateFirst)) {
            log.error("插入高级工程师证书一级相关信息表失败：tableName{}，jsonData：{}", tableName, jsonData);
            throw new RuntimeException("插入高级工程师证书一级相关信息表失败");
        }
        relationColumnData.put("seniorEngineerCertificateFirstId", seniorEngineerCertificateFirst.getId());
    }
}