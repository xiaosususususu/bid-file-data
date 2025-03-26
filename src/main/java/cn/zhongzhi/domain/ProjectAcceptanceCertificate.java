package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 工程竣工验收证书相关信息实体类，对应 project_acceptance_certificate 表
 */
@Data
@TableName("project_acceptance_certificate")
public class ProjectAcceptanceCertificate {
    /**
     * 主键，唯一标识工程竣工验收证书记录
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
     * 工程名称
     */
    private String projectName;

    /**
     * 开工日期
     */
    private String startDate;

    /**
     * 施工单位
     */
    private String contractor;

    /**
     * 竣工日期
     */
    private String completionDate;

    /**
     * 合同造价（万元）
     */
    private BigDecimal contractPrice;

    /**
     * 施工决算（万元）
     */
    private BigDecimal constructionSettlement;

    /**
     * 验收范围及数量
     */
    private String acceptanceScope;

    /**
     * 存在问题及处理意见
     */
    private String existingIssues;

    /**
     * 对工程质量的评价
     */
    private String qualityEvaluation;

    /**
     * 竣工验收日期
     */
    private String acceptanceDate;

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