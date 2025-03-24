package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 招投标项目实体类，对应数据库中的 tender_project 表
 */
@Data
@TableName("tender_project")
public class TenderProject {
    /**
     * 招投标项目的唯一标识，使用 UUID 自动分配
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 招标项目 ID
     */
    private String tenderProjectId;
    /**
     * 批复项目 ID
     */
    private String approvalProjectId;
    /**
     * 招标项目编号
     */
    private String tenderProjectCode;
    /**
     * 招标项目名称
     */
    private String tenderProjectName;
    /**
     * 招标项目类型
     */
    private String tenderProjectType;
    /**
     * 招标项目所在行政区域代码
     */
    private String regionCode;
    /**
     * 招标年度
     */
    private String tenderYear;
    /**
     * 招标内容与范围及招标方案说明
     */
    private String tenderContent;
    /**
     * 招标项目建立时间，日期时间型（yyyyMMddHHmmss），社会公开
     */
    private String createTime;
}