package cn.zhongzhi.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.zhongzhi.domain.*;
import cn.zhongzhi.mapper.*;
import cn.zhongzhi.service.ContractAgreementLastBasicInfoService;
import cn.zhongzhi.service.IStructuralDataPublicInterface;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Slf4j
public class ContractAgreementLastBasicInfoServiceImpl extends ServiceImpl<ContractAgreementLastBasicInfoMapper, ContractAgreementLastBasicInfo> implements ContractAgreementLastBasicInfoService, IStructuralDataPublicInterface {
    @Resource
    private ContractAgreementCoverMapper contractAgreementCoverMapper;
    @Resource
    private ContractAgreementFirstMapper contractAgreementFirstBasicInfoMapper;
    @Resource
    private ContractAgreementSecondMapper contractAgreementSecondMapper;
    @Resource
    private ClientInfoMapper clientInfoMapper;
    @Resource
    private ContractorInfoMapper contractorInfoMapper;

    @Transactional
    @Override
    public void dataOperation(String tableName, String jsonData, Map<String, String> relationColumnData) {
        JSONObject contractorLastJsonObject = JSONUtil.parseObj(jsonData);
        //1.获取基本信息
        ContractAgreementLastBasicInfo contractAgreementLastBasicInfo = contractorLastJsonObject.getBean("contract_agreement_last_basic_info", ContractAgreementLastBasicInfo.class);
        //ContractAgreementLastBasicInfo contractAgreementLastBasicInfo = JSONUtil.toBean(jsonData, ContractAgreementLastBasicInfo.class);
        if (ObjUtil.isEmpty(contractAgreementLastBasicInfo)) {
            return;
        }
        ClientInfo clientInfo = contractorLastJsonObject.getBean("client_info", ClientInfo.class);
        if (ObjUtil.isNotEmpty(clientInfo)) {
            clientInfo.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
            clientInfo.setFileId(relationColumnData.getOrDefault("fileId", ""));
            int clientInfoCount = clientInfoMapper.insert(clientInfo);
            if (clientInfoCount <= 0) {
                log.error("插入发包人信息表失败：tableName{}，jsonData：{}", "client_info", jsonData);
                throw new RuntimeException("插入发包人信息表失败");
            }
        }
        ContractorInfo contractorInfo = contractorLastJsonObject.getBean("contractor_info", ContractorInfo.class);
        if (ObjUtil.isNotEmpty(contractorInfo)) {
            contractorInfo.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
            contractorInfo.setFileId(relationColumnData.getOrDefault("fileId", ""));
            int contractorInfoCount = contractorInfoMapper.insert(contractorInfo);
            if (contractorInfoCount <= 0) {
                log.error("插入承包人信息表失败：tableName{}，jsonData：{}", "contractor_info", jsonData);
                throw new RuntimeException("插入承包人信息表失败");
            }
        }
        contractAgreementLastBasicInfo.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
        contractAgreementLastBasicInfo.setFileId(relationColumnData.getOrDefault("fileId", ""));
        contractAgreementLastBasicInfo.setContractAgreementCoverId(relationColumnData.getOrDefault("contractAgreementCoverId", ""));
        contractAgreementLastBasicInfo.setContractAgreementFirstId(relationColumnData.getOrDefault("contractAgreementFirstId", ""));
        contractAgreementLastBasicInfo.setContractAgreementSecondId(relationColumnData.getOrDefault("contractAgreementSecondId", ""));
        contractAgreementLastBasicInfo.setClientInfoId(clientInfo.getId());
        contractAgreementLastBasicInfo.setContractorInfoId(contractorInfo.getId());
        if (!this.save(contractAgreementLastBasicInfo)) {
            log.error("插入合同协议书尾页表失败：tableName{}，jsonData：{}", tableName, jsonData);
            throw new RuntimeException("插入合同协议书尾页表失败");
        }
        relationColumnData.put("contractAgreementLastBasicInfoId", contractAgreementLastBasicInfo.getId());
        if (relationColumnData.containsKey("contractAgreementSecondId")) {
            contractAgreementSecondMapper.updateById(ContractAgreementSecond.builder().id(relationColumnData.get("contractAgreementSecondId")).contractAgreementLastBasicInfoId(contractAgreementLastBasicInfo.getId()).build());
        }
        if (relationColumnData.containsKey("contractAgreementFirstId")) {
            contractAgreementFirstBasicInfoMapper.updateById(ContractAgreementFirst.builder().id(relationColumnData.get("contractAgreementFirstId")).contractAgreementLastBasicInfoId(contractAgreementLastBasicInfo.getId()).build());
        }
        if (relationColumnData.containsKey("contractAgreementCoverId")) {
            contractAgreementCoverMapper.updateById(ContractAgreementCover.builder().id(relationColumnData.get("contractAgreementCoverId")).contractAgreementLastBasicInfoId(contractAgreementLastBasicInfo.getId()).build());
        }

    }

}