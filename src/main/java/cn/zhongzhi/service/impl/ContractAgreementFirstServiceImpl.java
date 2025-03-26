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
import cn.zhongzhi.service.ContractAgreementFirstService;
import cn.zhongzhi.service.IStructuralDataPublicInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 合同协议首页相关信息的服务实现类，实现具体的业务逻辑
 */
@Service
@Slf4j
public class ContractAgreementFirstServiceImpl extends ServiceImpl<ContractAgreementFirstMapper, ContractAgreementFirst> implements ContractAgreementFirstService, IStructuralDataPublicInterface {
    @Resource
    private ContractAgreementCoverMapper contractAgreementCoverMapper;
    @Resource
    private ContractAgreementSecondMapper contractAgreementSecondMapper;
    @Resource
    private ContractAgreementLastBasicInfoMapper contractAgreementLastBasicInfoMapper;

    @Transactional
    @Override
    public void dataOperation(String tableName, String jsonData, Map<String, String> relationColumnData) {
        ContractAgreementFirst contractAgreementFirst = JSONUtil.toBean(jsonData, ContractAgreementFirst.class);
        contractAgreementFirst.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
        contractAgreementFirst.setFileId(relationColumnData.getOrDefault("fileId", ""));
        contractAgreementFirst.setContractAgreementCoverId(relationColumnData.getOrDefault("contractAgreementCoverId", ""));
        contractAgreementFirst.setContractAgreementSecondId(relationColumnData.getOrDefault("contractAgreementSecondId", ""));
        contractAgreementFirst.setContractAgreementLastBasicInfoId(relationColumnData.getOrDefault("contractAgreementLastBasicInfoId", ""));
        if (!this.save(contractAgreementFirst)) {
            log.error("插入合同协议首页表失败：tableName{}，jsonData：{}", tableName, jsonData);
            throw new RuntimeException("插入合同协议首页表失败");
        }
        relationColumnData.put("contractAgreementFirstId", contractAgreementFirst.getId());
        if (relationColumnData.containsKey("contractAgreementLastBasicInfoId")) {
            contractAgreementLastBasicInfoMapper.updateById(ContractAgreementLastBasicInfo.builder().id(relationColumnData.get("contractAgreementLastBasicInfoId")).contractAgreementFirstId(contractAgreementFirst.getId()).build());
        }
        if (relationColumnData.containsKey("contractAgreementSecondId")) {
            contractAgreementSecondMapper.updateById(ContractAgreementSecond.builder().id(relationColumnData.get("contractAgreementSecondId")).contractAgreementFirstId(contractAgreementFirst.getId()).build());
        }
        if (relationColumnData.containsKey("contractAgreementCoverId")) {
            contractAgreementCoverMapper.updateById(ContractAgreementCover.builder().id(relationColumnData.get("contractAgreementCoverId")).contractAgreementFirstId(contractAgreementFirst.getId()).build());
        }
    }

    @Override
    public Map<String, Object> organizeContractData(String bidderId) {
        //1.第一页
        List<ContractAgreementFirst> contractAgreementFirstList = this.lambdaQuery().eq(ContractAgreementFirst::getBidderId, bidderId).list();
        for (ContractAgreementFirst contractAgreementFirst : contractAgreementFirstList) {
            contractAgreementSecondMapper.selectOne(new QueryWrapper<ContractAgreementSecond>().eq("", contractAgreementFirst.getContractAgreementSecondId()));
        }
        //2.第二页

        //3.第三页

        return Map.of();
    }
}