package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 工程竣工验收证书验收单位意见相关信息实体类，对应 project_acceptance_certificate_acceptance_status 表
 */
@Data
@TableName("project_acceptance_certificate_acceptance_status")
public class ProjectAcceptanceCertificateAcceptanceStatus {
    /**
     * 主键，唯一标识工程竣工验收证书验收单位意见记录
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 关联的工程竣工验收证书的 ID
     */
    private String projectAcceptanceCertificateId;

    /**
     * 投标人的唯一标识
     */
    private String bidderId;

    /**
     * 关联的附件的唯一标识
     */
    private String fileId;

    /**
     * 建设单位是否同意验收
     */
    private String constructionUnit;

    /**
     * 设计单位是否同意验收
     */
    private String designUnit;

    /**
     * 监理单位是否同意验收
     */
    private String supervisionUnit;

    /**
     * 施工单位是否同意验收
     */
    private String contractorUnit;

    /**
     * 勘察单位是否同意验收
     */
    private String surveyUnit;

    /**
     * 邀请单位是否同意验收
     */
    private String invitedUnit;

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