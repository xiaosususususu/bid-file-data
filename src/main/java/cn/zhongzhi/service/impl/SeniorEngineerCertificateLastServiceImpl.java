package cn.zhongzhi.service.impl;

import cn.hutool.json.JSONUtil;
import cn.zhongzhi.domain.SeniorEngineerCertificateLast;
import cn.zhongzhi.mapper.SeniorEngineerCertificateLastMapper;
import cn.zhongzhi.service.IStructuralDataPublicInterface;
import cn.zhongzhi.service.SeniorEngineerCertificateLastService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 高级工程师证书末页相关信息服务实现类
 */
@Service
@Slf4j
public class SeniorEngineerCertificateLastServiceImpl extends ServiceImpl<SeniorEngineerCertificateLastMapper, SeniorEngineerCertificateLast> implements SeniorEngineerCertificateLastService, IStructuralDataPublicInterface {
    @Override
    public void dataOperation(String tableName, String jsonData, Map<String, String> relationColumnData) {
        SeniorEngineerCertificateLast seniorEngineerCertificateLast = JSONUtil.toBean(jsonData, SeniorEngineerCertificateLast.class);
        seniorEngineerCertificateLast.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
        seniorEngineerCertificateLast.setFileId(relationColumnData.getOrDefault("fileId", ""));
        seniorEngineerCertificateLast.setSeniorEngineerCertificateFirstId(relationColumnData.getOrDefault("seniorEngineerCertificateFirstId", ""));
        if (!this.save(seniorEngineerCertificateLast)) {
            log.error("插入高级工程师证书末页相关信息表失败：tableName{}，jsonData：{}", tableName, jsonData);
            throw new RuntimeException("插入高级工程师证书末页相关信息表失败");
        }
        relationColumnData.put("seniorEngineerCertificateLastId", seniorEngineerCertificateLast.getId());
    }
}