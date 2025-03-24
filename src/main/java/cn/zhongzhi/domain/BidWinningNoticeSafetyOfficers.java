package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 中标标段通知书安全员相关信息实体类，对应 bid_winning_notice_safety_officers 表
 */
@Data
@TableName("bid_winning_notice_safety_officers")
public class BidWinningNoticeSafetyOfficers {
    /**
     * 主键，唯一标识中标标段通知书安全员记录
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 外键，关联中标标段通知书末页信息的记录 ID
     */
    private String bidWinningNoticeLastId;

    /**
     * 安全员的姓名
     */
    private String name;

    /**
     * 安全员的岗位证书编号
     */
    private String positionCertificate;

    /**
     * 安全员的安全生产 C 证编号
     */
    private String safetyCertificate;

    /**
     * 安全员的身份证号
     */
    private String idNumber;

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