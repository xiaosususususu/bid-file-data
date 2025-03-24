package cn.zhongzhi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 标段（Bid Section）实体类，对应数据库中的 tender_project_section 表
 */
@Data
@TableName("tender_project_section")
public class TenderProjectSection {
    /**
     * 标段的唯一标识，使用 UUID 自动分配
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 标段（包）所属招标项目 ID
     */
    private String tenderProjectId;
    /**
     * 标段（包）名称
     */
    private String bidSectionName;
    /**
     * 标段（包）所在行政区域代码
     */
    private String regionCode;
    /**
     * 标段（包）内容
     */
    private String bidSectionContent;
    /**
     * 标段（包）分类代码
     */
    private String tenderProjectClassifyCode;
    /**
     * 标段（包）投标价格
     */
    private BigDecimal bidPrice;
    /**
     * 标段合同估算价
     */
    private BigDecimal contractReckonPrice;
    /**
     * 最高限价
     */
    private BigDecimal ceilingPrice;
    /**
     * 投标人资格条件
     */
    private String bidQualification;
    /**
     * 标段(包)建立时间
     */
    private LocalDateTime createTime;
    /**
     * 招标项目 id，政务公开
     */
    private String projectId;
    /**
     * 批复项目 id，政务公开
     */
    private String approveProjectId;
    /**
     * 招标项目编号，社会公开，同一招标项目的不同表中该编号必须一致
     */
    private String tenderProjectCode;
    /**
     * 统一交易标识码，政务公开，此码在同笔交易相关的所有表中必须一致
     */
    private String unifiedDealCode;
    /**
     * 标段(包)招标次数，社会公开，适用流标后重新招标时仍然采用原项目信息，并没有用新项目信息情况
     */
    private Integer bidEctionNum;
    /**
     * 标段合同估算价币种代码，社会公开，采用 GB/T 12406 - 2008《表示货币和资金的代码》的数字码，例如，人民币是 156
     */
    private String currencyCode;
    /**
     * 标段合同估算价单位，社会公开，工程建设招标投标数据集的价格金额以元为单位，元的金额单位代码为 0
     */
    private String priceUnit;
}