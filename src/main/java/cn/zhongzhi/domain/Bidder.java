package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 投标人（Bidder）实体类，对应数据库中的 bidder 表
 */
@Data
@TableName("bidder")
public class Bidder {
    /**
     * 投标人的唯一标识，使用 UUID 自动分配
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 投标人名称
     */
    private String supplierName;
    /**
     * 项目经理名称
     */
    private String projectManagerName;
    /**
     * 费率或其它类型报价
     */
    private String otherBidPrice;
    /**
     * 标段 ID
     */
    private String bidSectionId;
    /**
     * 项目经理证件号
     */
    private String projectManagerIdNumber;
    /**
     * 报名方式
     */
    private String registrationMethod;
    /**
     * 项目经理等级
     */
    private String projectManagerLevel;
    /**
     * 投标单位类型
     */
    private String bidUnitType;
    /**
     * 投标单位注册地址
     */
    private String bidUnitAddress;
    /**
     * 基本存款账户
     */
    private String basicAccount;
    /**
     * 投标人资质信息
     */
    private String qualifications;
    /**
     * 组织机构代码证
     */
    private String organizationcode;
    /**
     * 投标文件递交时间
     */
    private LocalDateTime fileUploadTime;
    /**
     * 投标单位项目负责人
     */
    private String projectLeader;
    /**
     * 投标单位项目负责人联系方式
     */
    private String projectLeaderNumber;
    /**
     * 投标单位项目负责人身份证号码
     */
    private String projectLeaderCode;
    /**
     * 投标状态
     */
    private String bidStatus;
    /**
     * 投标联系人电话
     */
    private String contractPhone;
    /**
     * 投标联系人名称
     */
    private String contactName;
    /**
     * 投标联系人身份证号码
     */
    private String contactCode;
}