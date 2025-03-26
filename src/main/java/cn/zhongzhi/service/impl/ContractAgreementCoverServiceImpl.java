package cn.zhongzhi.service.impl;

import cn.hutool.json.JSONUtil;
import cn.zhongzhi.domain.ContractAgreementCover;
import cn.zhongzhi.domain.ContractAgreementFirst;
import cn.zhongzhi.domain.ContractAgreementLastBasicInfo;
import cn.zhongzhi.domain.ContractAgreementSecond;
import cn.zhongzhi.mapper.ContractAgreementCoverMapper;
import cn.zhongzhi.mapper.ContractAgreementFirstMapper;
import cn.zhongzhi.mapper.ContractAgreementLastBasicInfoMapper;
import cn.zhongzhi.mapper.ContractAgreementSecondMapper;
import cn.zhongzhi.service.ContractAgreementCoverService;
import cn.zhongzhi.service.IStructuralDataPublicInterface;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 合同协议封面相关信息的服务实现类，实现具体的业务逻辑
 */
@Service
@Slf4j
public class ContractAgreementCoverServiceImpl extends ServiceImpl<ContractAgreementCoverMapper, ContractAgreementCover> implements ContractAgreementCoverService, IStructuralDataPublicInterface {
    @Resource
    private ContractAgreementFirstMapper contractAgreementFirstMapper;
    @Resource
    private ContractAgreementSecondMapper contractAgreementSecondMapper;
    @Resource
    private ContractAgreementLastBasicInfoMapper contractAgreementLastBasicInfoMapper;

    @Transactional
    @Override
    public void dataOperation(String tableName, String jsonData, Map<String, String> relationColumnData) {
        ContractAgreementCover contractAgreementCover = JSONUtil.toBean(jsonData, ContractAgreementCover.class);
        contractAgreementCover.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
        contractAgreementCover.setFileId(relationColumnData.getOrDefault("fileId", ""));
        contractAgreementCover.setContractAgreementFirstId(relationColumnData.getOrDefault("contractAgreementFirstId", ""));
        contractAgreementCover.setContractAgreementSecondId(relationColumnData.getOrDefault("contractAgreementSecondId", ""));
        contractAgreementCover.setContractAgreementLastBasicInfoId(relationColumnData.getOrDefault("contractAgreementLastBasicInfoId", ""));
        if (!this.save(contractAgreementCover)) {
            log.error("插入合同协议封面表失败：tableName{}，jsonData：{}", tableName, jsonData);
            throw new RuntimeException("插入合同协议首页表失败");
        }
        relationColumnData.put("contractAgreementCoverId", contractAgreementCover.getId());
        if (relationColumnData.containsKey("contractAgreementLastBasicInfoId")) {
            contractAgreementLastBasicInfoMapper.updateById(ContractAgreementLastBasicInfo.builder().id(relationColumnData.get("contractAgreementLastBasicInfoId")).contractAgreementCoverId(contractAgreementCover.getId()).build());
        }
        if (relationColumnData.containsKey("contractAgreementSecondId")) {
            contractAgreementSecondMapper.updateById(ContractAgreementSecond.builder().id(relationColumnData.get("contractAgreementSecondId")).contractAgreementCoverId(contractAgreementCover.getId()).build());
        }
        if (relationColumnData.containsKey("contractAgreementFirstId")) {
            contractAgreementFirstMapper.updateById(ContractAgreementFirst.builder().id(relationColumnData.get("contractAgreementFirstId")).contractAgreementCoverId(contractAgreementCover.getId()).build());
        }
    }
}