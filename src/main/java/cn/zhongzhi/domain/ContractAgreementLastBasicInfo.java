package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@TableName("contract_agreement_last_basic_info")
public class ContractAgreementLastBasicInfo {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String bidderId;

    private String fileId;

    private String contractAgreementCoverId;

    private String contractAgreementFirstId;

    private String contractAgreementSecondId;

    private String signingDate;

    private String signingLocation;

    private String clientInfoId;

    private String contractorInfoId;

    private LocalDateTime createTime;

    @TableField(update = "CURRENT_TIMESTAMP")
    private LocalDateTime updateTime;
}