package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 银行开户许可证相关信息实体类
 */
@Data
@TableName("bank_account_opening_license")
public class BankAccountOpeningLicense {
    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 投标人 ID
     */
    private String bidderId;

    /**
     * 附件 ID
     */
    private String fileId;

    /**
     * 编号
     */
    private String certificateNumber;

    /**
     * 存款人名称
     */
    private String companyName;

    /**
     * 开户银行
     */
    private String issuingBank;

    /**
     * 法定代表人
     */
    private String legalRepresentative;

    /**
     * 核准号
     */
    private String approvalNumber;

    /**
     * 账号
     */
    private String accountNumber;

    /**
     * 发证日期
     */
    private String issueDate;

    /**
     * 发证机关
     */
    private String issuingAuthority;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(update = "CURRENT_TIMESTAMP")
    private LocalDateTime updateTime;
}