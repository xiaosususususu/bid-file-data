package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 合同协议第二页相关信息实体类，对应 contract_agreement_second 表
 */
@Builder
@Data
@TableName("contract_agreement_second")
public class ContractAgreementSecond {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 投标人 ID
     */
    @TableField("bidder_id")
    private String bidderId;

    /**
     * 附件 ID
     */
    @TableField("file_id")
    private String fileId;

    /**
     * 合同协议封面 ID
     */
    @TableField("contract_agreement_cover_id")
    private String contractAgreementCoverId;

    /**
     * 合同协议第一页 ID
     */
    @TableField("contract_agreement_first_id")
    private String contractAgreementFirstId;

    /**
     * 合同协议末页基本信息 ID
     */
    @TableField("contract_agreement_last_basic_info_id")
    private String contractAgreementLastBasicInfoId;

    /**
     * 工程质量是否合格
     */
    @TableField("quality_compliance")
    private String qualityCompliance;

    /**
     * 保修要求
     */
    @TableField("warranty_requirement")
    private String warrantyRequirement;

    /**
     * 签约合同价-人民币（大写）
     */
    @TableField("signing_contract_price_rmb_capital")
    private String signingContractPriceRmbCapital;

    /**
     * 签约合同价-安全文明费（RMB）
     */
    @TableField("safety_fee")
    private String safetyFee;

    /**
     * 签约合同价-材料工程暂估价（RMB）
     */
    @TableField("material_project_evaluate_fee")
    private String materialProjectEvaluateFee;

    /**
     * 签约合同价-专业工程暂估价（RMB）
     */
    @TableField("major_project_evaluate_fee")
    private String majorProjectEvaluateFee;

    /**
     * 签约合同价-销项税额（RMB）
     */
    @TableField("sales_tax_amount")
    private String salesTaxAmount;

    /**
     * 签约合同价-附加税额（RMB）
     */
    @TableField("additional_tax_amount")
    private String additionalTaxAmount;

    /**
     * 签约合同价-其他项目费（RMB）
     */
    @TableField("other_project_fees")
    private String otherProjectFees;

    /**
     * 签约合同价-暂列金额（RMB）
     */
    @TableField("provisional_sum")
    private String provisionalSum;

    /**
     * 签约合同价-暂估价（RMB）
     */
    @TableField("provisional_valuation")
    private String provisionalValuation;

    /**
     * 签约合同价-优惠（RMB）
     */
    @TableField("discount_amount")
    private String discountAmount;

    /**
     * 交易服务费
     */
    @TableField("transaction_service_fee")
    private String transactionServiceFee;

    /**
     * 合同价格形式
     */
    @TableField("contractor_price_form")
    private String contractorPriceForm;

    /**
     * 项目经理
     */
    @TableField("contractor_project_manager")
    private String contractorProjectManager;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
}