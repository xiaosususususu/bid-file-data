package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author suchengbo
 */
@Data
@TableName("legal_representative")
public class LegalRepresentative {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String bidderId;
    private String fileId;
    private String name;
    private String gender;
    private String idCardNumber;
    private LocalDateTime birthDate;
    private String address;
    private String contactPhone;
    private String email;
    private String legalRepresentativeBackId;
    private LocalDateTime createTime;
    @TableField(update = "CURRENT_TIMESTAMP")
    private LocalDateTime updateTime;
}