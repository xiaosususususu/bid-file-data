package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 中标标段通知书相关信息实体类，对应 bid_winning_notice_last 表
 */
@Data
@TableName("bid_winning_notice_last")
public class BidWinningNoticeLast {
    /**
     * 主键，唯一标识中标标段通知书记录
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
     * 质量员的姓名
     */
    private String qualityOfficerName;

    /**
     * 质量员的岗位证书编号
     */
    private String qualityOfficerCertificateNumber;

    /**
     * 质量员的身份证号码
     */
    private String qualityOfficerIdNumber;

    /**
     * 履约保证金的金额
     */
    private String performanceBondAmount;

    /**
     * 履约保证金提交的截止时间
     */
    private String submissionDeadline;

    /**
     * 建设单位的签章信息
     */
    private String contractSignatureAuthority;

    /**
     * 招标代理机构的签章信息
     */
    private String biddingAgencySignature;

    /**
     * 中标备案的日期
     */
    private String recordStampDate;

    /**
     * 外键，关联中标通知书首页信息的记录 ID
     */
    private String bidWinningNoticeFirstId;
    /**
     * 安全员列表
     */
    private List<BidWinningNoticeSafetyOfficers> bidWinningNoticeSafetyOfficersList;
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