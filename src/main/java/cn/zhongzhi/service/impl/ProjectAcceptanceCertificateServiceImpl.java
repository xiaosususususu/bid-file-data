package cn.zhongzhi.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.zhongzhi.domain.ProjectAcceptanceCertificate;
import cn.zhongzhi.domain.ProjectAcceptanceCertificateAcceptanceStatus;
import cn.zhongzhi.mapper.ProjectAcceptanceCertificateAcceptanceStatusMapper;
import cn.zhongzhi.mapper.ProjectAcceptanceCertificateMapper;
import cn.zhongzhi.service.IStructuralDataPublicInterface;
import cn.zhongzhi.service.ProjectAcceptanceCertificateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 工程竣工验收证书相关信息的服务实现类，实现具体的业务逻辑
 */
@Service
@Slf4j
public class ProjectAcceptanceCertificateServiceImpl extends ServiceImpl<ProjectAcceptanceCertificateMapper, ProjectAcceptanceCertificate> implements ProjectAcceptanceCertificateService, IStructuralDataPublicInterface {
    @Resource
    private ProjectAcceptanceCertificateAcceptanceStatusMapper projectAcceptanceCertificateAcceptanceStatusMapper;

    @Transactional
    @Override
    public void dataOperation(String tableName, String jsonData, Map<String, String> relationColumnData) {
        ProjectAcceptanceCertificate projectAcceptanceCertificate = JSONUtil.toBean(jsonData, ProjectAcceptanceCertificate.class);
        projectAcceptanceCertificate.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
        projectAcceptanceCertificate.setFileId(relationColumnData.getOrDefault("fileId", ""));
        if (!this.save(projectAcceptanceCertificate)) {
            log.error("插入工程竣工验收证书相关信息表失败：tableName{}，jsonData：{}", tableName, jsonData);
            throw new RuntimeException("插入工程竣工验收证书相关信息表失败");
        }
        JSONObject projectAcceptanceCertificateJsonObject = JSONUtil.parseObj(jsonData);
        ProjectAcceptanceCertificateAcceptanceStatus acceptanceStatus = projectAcceptanceCertificateJsonObject.getBean("acceptance_status", ProjectAcceptanceCertificateAcceptanceStatus.class);
        acceptanceStatus.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
        acceptanceStatus.setFileId(relationColumnData.getOrDefault("fileId", ""));
        acceptanceStatus.setProjectAcceptanceCertificateId(projectAcceptanceCertificate.getId());
        int count = projectAcceptanceCertificateAcceptanceStatusMapper.insert(acceptanceStatus);
        if (count <= 0) {
            log.error("插入竣工单位验收表失败：tableName{}，jsonData：{}", "project_acceptance_certificate_acceptance_status", jsonData);
            throw new RuntimeException("插入竣工单位验收表失败");
        }

    }
}