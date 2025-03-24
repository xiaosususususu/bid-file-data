package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 高级工程师证书末页相关信息实体类
 */
@Data
@TableName("senior_engineer_certificate_last")
public class SeniorEngineerCertificateLast {
    /**
     * 主键 ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 投标人 ID
     */
    private String bidderId;

    /**
     * 附件 ID
     */
    private String fileId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 出生年月
     */
    private String birthDate;

    /**
     * 工作单位
     */
    private String workUnit;

    /**
     * 现从事专业
     */
    private String currentSpecialty;

    /**
     * 原专业技术职务资格
     */
    private String previousQualification;

    /**
     * 现专业技术职务资格
     */
    private String currentQualification;

    /**
     * 资格证书编号
     */
    private String certificateNumber;

    /**
     * 评审时间
     */
    private String reviewDate;

    /**
     * 公布时间
     */
    private String announcementDate;

    /**
     * 公布文号
     */
    private String approvalDocument;
    /**
     * 外键
     */
    private String seniorEngineerCertificateFirstId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(update = "CURRENT_TIMESTAMP")
    private LocalDateTime updateTime;
}