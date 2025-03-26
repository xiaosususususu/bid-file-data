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
import cn.zhongzhi.service.ContractAgreementSecondService;
import cn.zhongzhi.service.IStructuralDataPublicInterface;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 合同协议第二页相关信息的服务实现类，实现具体的业务逻辑
 */
@Service
@Slf4j
public class ContractAgreementSecondServiceImpl extends ServiceImpl<ContractAgreementSecondMapper, ContractAgreementSecond> implements ContractAgreementSecondService, IStructuralDataPublicInterface {

    @Resource
    private ContractAgreementCoverMapper contractAgreementCoverMapper;
    @Resource
    private ContractAgreementFirstMapper contractAgreementFirstBasicInfoMapper;
    @Resource
    private ContractAgreementLastBasicInfoMapper contractAgreementLastBasicInfoMapper;

    @Transactional
    @Override
    public void dataOperation(String tableName, String jsonData, Map<String, String> relationColumnData) {
        ContractAgreementSecond contractAgreementSecond = JSONUtil.toBean(jsonData, ContractAgreementSecond.class);
        contractAgreementSecond.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
        contractAgreementSecond.setFileId(relationColumnData.getOrDefault("fileId", ""));
        contractAgreementSecond.setContractAgreementCoverId(relationColumnData.getOrDefault("contractAgreementCoverId", ""));
        contractAgreementSecond.setContractAgreementFirstId(relationColumnData.getOrDefault("contractAgreementFirstId", ""));
        contractAgreementSecond.setContractAgreementLastBasicInfoId(relationColumnData.getOrDefault("contractAgreementLastBasicInfoId", ""));
        if (!this.save(contractAgreementSecond)) {
            log.error("插入合同协议书第二页表失败：tableName{}，jsonData：{}", tableName, jsonData);
            throw new RuntimeException("插入合同协议书第二页表失败");
        }
        relationColumnData.put("contractAgreementSecondId", contractAgreementSecond.getId());
        if (relationColumnData.containsKey("contractAgreementLastBasicInfoId")) {
            contractAgreementLastBasicInfoMapper.updateById(ContractAgreementLastBasicInfo.builder().id(relationColumnData.get("contractAgreementLastBasicInfoId")).contractAgreementSecondId(contractAgreementSecond.getId()).build());
        }
        if (relationColumnData.containsKey("contractAgreementFirstId")) {
            contractAgreementFirstBasicInfoMapper.updateById(ContractAgreementFirst.builder().id(relationColumnData.get("contractAgreementFirstId")).contractAgreementSecondId(contractAgreementSecond.getId()).build());
        }
        if (relationColumnData.containsKey("contractAgreementCoverId")) {
            contractAgreementCoverMapper.updateById(ContractAgreementCover.builder().id(relationColumnData.get("contractAgreementCoverId")).contractAgreementSecondId(contractAgreementSecond.getId()).build());
        }
    }
}