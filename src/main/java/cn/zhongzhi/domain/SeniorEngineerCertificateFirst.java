package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 高级工程师证书一级相关信息实体类
 */
@Data
@TableName("senior_engineer_certificate_first")
public class SeniorEngineerCertificateFirst {
    /**
     * 主键 ID
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
     * 持证人签名（持证人亲笔签名字段（图片中为空白区域））
     */
    private String holderSignature;
    /**
     * 外键
     */
    private String seniorEngineerCertificateLastId;

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