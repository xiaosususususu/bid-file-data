package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 批复项目实体类，对应数据库中的 project_approval 表
 */
@Data
@TableName("project_approval")
public class ProjectApproval {
    /**
     * 批复项目的唯一标识，使用 UUID 自动分配
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 批复项目名称
     */
    private String projectName;
    /**
     * 项目所在行政区域代码
     */
    private String regionCode;
    /**
     * 项目地址
     */
    private String address;
    /**
     * 项目编号
     */
    private String projectCode;
    /**
     * 项目描述
     */
    private String projectDescription;
    /**
     * 是否重点项目
     */
    private String isImportant;
    /**
     * 项目创建时间
     */
    private LocalDateTime createTime;
    /**
     * 项目审批单位，自由文本，社会公开
     */
    private String approvalAuthority;
}