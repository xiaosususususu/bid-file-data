package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 法定代表人信息实体类，对应 legal_representative 表
 */
@Builder
@Data
@TableName("legal_representative")
public class LegalRepresentative {
    /**
     * 唯一标识
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 招标单位 ID
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
     * 民族
     */
    private String nation;

    /**
     * 地址
     */
    private String address;

    /**
     * 出生日期
     */
    private Date birthDate;

    /**
     * 法定代表人背面信息 ID
     */
    private String legalRepresentativeBackId;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 记录创建时间
     */
    private Date createTime;

    /**
     * 记录更新时间
     */
    @TableField(update = "CURRENT_TIMESTAMP")
    private Date updateTime;
}