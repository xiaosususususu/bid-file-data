package cn.zhongzhi.mapper;

import cn.zhongzhi.domain.BankAccountOpeningLicense;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 银行开户许可证相关信息 Mapper 接口
 */
@Mapper
public interface BankAccountOpeningLicenseMapper extends BaseMapper<BankAccountOpeningLicense> {
}