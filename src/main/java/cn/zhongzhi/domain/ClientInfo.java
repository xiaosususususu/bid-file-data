package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 发包人信息相关信息实体类，对应 client_info 表
 */
@Data
@TableName("client_info")
public class ClientInfo {
    /**
     * 主键，唯一标识发包人信息记录
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
     * 发包人是否盖章
     */
    private Boolean sealStatus;

    /**
     * 法定代表人或其委托代理人 - 签字
     */
    private String legalRepresentativeSignature;

    /**
     * 经办人 - 签字
     */
    private String operatorSignature;

    /**
     * 组织机构代码
     */
    private String organizationCode;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 法定代表人
     */
    private String legalRepresentative;

    /**
     * 委托代理人
     */
    private String authorizedAgent;

    /**
     * 电话
     */
    private String phone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 电子信箱
     */
    private String email;

    /**
     * 开户银行
     */
    private String bankName;

    /**
     * 账号
     */
    private String bankAccount;

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