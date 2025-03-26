package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 合同协议封面相关信息实体类，对应 contract_agreement_cover 表
 */
@Builder
@Data
@TableName("contract_agreement_cover")
public class ContractAgreementCover {
    /**
     * 主键，唯一标识合同协议封面记录
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
     * 合同协议书第一页的 ID
     */
    private String contractAgreementFirstId;

    /**
     * 合同协议书第二页的 ID
     */
    private String contractAgreementSecondId;

    /**
     * 合同协议书尾页的 ID
     */
    private String contractAgreementLastBasicInfoId;

    /**
     * 合同的编号
     */
    private String contractNumber;

    /**
     * 项目的名称
     */
    private String projectName;

    /**
     * 甲方的名称
     */
    private String client;

    /**
     * 乙方的名称
     */
    private String contractor;

    /**
     * 合同的签约地点
     */
    private String signingLocation;

    /**
     * 合同的签订日期
     */
    private String contractDate;

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