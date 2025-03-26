package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 合同协议首页相关信息实体类，对应 contract_agreement_first 表
 */
@Builder
@Data
@TableName("contract_agreement_first")
public class ContractAgreementFirst {
    /**
     * 主键，唯一标识合同协议首页记录
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 投标人的唯一标识
     */
    private String bidderId;

    /**
     * 关联的附件的唯一标识
     */
    private String fileId;

    /**
     * 合同协议封面的 ID
     */
    private String contractAgreementCoverId;

    /**
     * 合同协议书第二页的 ID
     */
    private String contractAgreementSecondId;

    /**
     * 合同协议书尾页的 ID
     */
    private String contractAgreementLastBasicInfoId;

    /**
     * 发包人名称
     */
    private String client;

    /**
     * 承包人名称
     */
    private String contractor;

    /**
     * 工程名称
     */
    private String projectName;

    /**
     * 工程地点
     */
    private String projectLocation;

    /**
     * 工程立项批准文号
     */
    private String projectApprovalNumber;

    /**
     * 资金来源
     */
    private String fundingSource;

    /**
     * 计划开工日期
     */
    private String startDate;

    /**
     * 计划竣工日期
     */
    private String completionDate;

    /**
     * 工程质量是否合格
     */
    private String qualityCompliance;

    /**
     * 签约合同价 - 人民币（大写）
     */
    private String signingContractPriceRmbCapital;

    /**
     * 签约合同价 - 直接费用（RMB）
     */
    private String directCost;

    /**
     * 签约合同价 - 各项费用和利润（RMB）
     */
    private String feesAndProfit;

    /**
     * 签约合同价 - 安全文明费（RMB）
     */
    private String safetyFee;

    /**
     * 签约合同价 - 规费（RMB）
     */
    private String regulatoryFee;

    /**
     * 签约合同价 - 建安费用（RMB）
     */
    private String constructionCost;

    /**
     * 记录创建的时间，自动设置为当前时间
     */
    private LocalDateTime createTime;

    /**
     * 记录更新的时间，在记录更新时自动更新为当前时间
     */
    @TableField(update = "CURRENT_TIMESTAMP")
    private LocalDateTime updateTime;

}