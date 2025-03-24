package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 中标通知书施工员相关信息实体类，对应 bid_winning_notice_construction_crew 表
 */
@Data
@TableName("bid_winning_notice_construction_crew")
public class BidWinningNoticeConstructionCrew {
    /**
     * 主键，唯一标识中标通知书施工员记录
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 外键，关联中标通知书首页信息的记录 ID
     */
    private String bidWinningNoticeFirstId;

    /**
     * 施工员的姓名
     */
    private String name;

    /**
     * 施工员的岗位证书编号
     */
    private String positionCertificate;

    /**
     * 施工员的身份证号
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