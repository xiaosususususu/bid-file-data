package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 投标人与标段关系实体类，对应数据库中的 bidder_section_relation 表
 */
@Data
@TableName("bidder_section_relation")
public class BidderSectionRelation {
    /**
     * 关系的唯一标识，使用 UUID 自动分配
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 投标人 ID
     */
    private String bidderId;
    /**
     * 标段 ID
     */
    private String sectionId;
    /**
     * 投标日期
     */
    private LocalDateTime bidDate;
}