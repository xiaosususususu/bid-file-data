package cn.zhongzhi.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.zhongzhi.domain.BankAccountOpeningLicense;
import cn.zhongzhi.mapper.BankAccountOpeningLicenseMapper;
import cn.zhongzhi.service.BankAccountOpeningLicenseService;
import cn.zhongzhi.service.IStructuralDataPublicInterface;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 银行开户许可证相关信息服务实现类
 * @author suchengbo
 */
@Service
@Slf4j
public class BankAccountOpeningLicenseServiceImpl extends ServiceImpl<BankAccountOpeningLicenseMapper, BankAccountOpeningLicense> implements BankAccountOpeningLicenseService, IStructuralDataPublicInterface {
    @Override
    @Transactional
    public void dataOperation(String tableName, String jsonData, Map<String, String> relationColumnData) {
        JSONObject openLicenseJsonObject = JSONUtil.parseObj(jsonData);
        BankAccountOpeningLicense bankAccountOpeningLicense = openLicenseJsonObject.getBean("bank_account_opening_license", BankAccountOpeningLicense.class);
        if (ObjUtil.isEmpty(bankAccountOpeningLicense)) {
            bankAccountOpeningLicense = JSONUtil.toBean(jsonData, BankAccountOpeningLicense.class);
        }
        bankAccountOpeningLicense.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
        bankAccountOpeningLicense.setFileId(relationColumnData.getOrDefault("fileId", ""));
        if (!this.save(bankAccountOpeningLicense)) {
            log.error("插入银行开户许可证相关信息表失败：tableName{}，jsonData：{}", tableName, jsonData);
            throw new RuntimeException("插入银行开户许可证相关信息表失败");
        }
    }
}