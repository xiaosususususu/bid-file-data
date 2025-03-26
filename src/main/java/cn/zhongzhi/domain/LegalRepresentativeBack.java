package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
@TableName("legal_representative_back")
public class LegalRepresentativeBack {
    @TableId
    private String id;
    private String bidderId;
    private String fileId;
    private String legalRepresentativeId;
    private String validPeriodEnd;
    private String issuingAuthority;
    private String validPeriodStart;
    private Date createTime;
    @TableField(update = "CURRENT_TIMESTAMP")
    private Date updateTime;
}