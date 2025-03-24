package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 中标通知书相关信息实体类，对应 bid_winning_notice_first 表
 */
@Data
@TableName("bid_winning_notice_first")
public class BidWinningNoticeFirst {
    /**
     * 主键，唯一标识中标通知书记录
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
     * 中标通知书的编号
     */
    private String noticeNumber;

    /**
     * 项目的编号
     */
    private String projectNumber;

    /**
     * 中标的公司名称
     */
    private String companyName;

    /**
     * 道路的长度信息
     */
    private String roadLength;

    /**
     * 道路的宽度信息
     */
    private String roadWidth;

    /**
     * 中标金额的大写表示
     */
    private String bidAmountCapitalized;

    /**
     * 中标金额的小写数值
     */
    private String bidAmountNumeric;

    /**
     * 项目的直接费用
     */
    private String directCost;

    /**
     * 安全文明施工相关的费用
     */
    private String safetyFee;

    /**
     * 社会保险方面的费用
     */
    private String socialInsurance;

    /**
     * 项目的工期信息
     */
    private String constructionPeriod;

    /**
     * 项目负责人的姓名
     */
    private String projectManagerName;

    /**
     * 项目负责人的注册证编号
     */
    private String projectManagerRegistrationNumber;

    /**
     * 项目负责人的安全 B 证编号
     */
    private String projectManagerSafetyCertificate;

    /**
     * 项目负责人的身份证号码
     */
    private String projectManagerIdNumber;

    /**
     * 技术负责人的姓名
     */
    private String technicalLeaderName;

    /**
     * 技术负责人的岗位证书编号
     */
    private String technicalLeaderCertificateNumber;

    /**
     * 技术负责人的身份证号码
     */
    private String technicalLeaderIdNumber;

    /**
     * 外键，关联中标通知书末页信息的记录 ID
     */
    private String bidWinningNoticeLastId;
    /**
     *  bidWinningNoticeConstructionCrewList 施工员
     */
    private List<BidWinningNoticeConstructionCrew> bidWinningNoticeConstructionCrewList
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