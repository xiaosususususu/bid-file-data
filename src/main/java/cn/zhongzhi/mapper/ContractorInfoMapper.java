package cn.zhongzhi.mapper;

import cn.zhongzhi.domain.ContractorInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 承包人信息相关信息的 Mapper 接口，用于与数据库进行交互
 */
@Mapper
public interface ContractorInfoMapper extends BaseMapper<ContractorInfo> {
}