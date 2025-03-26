package cn.zhongzhi.mapper;

import cn.zhongzhi.domain.ContractAgreementCover;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 合同协议封面相关信息的 Mapper 接口，用于与数据库进行交互
 */
@Mapper
public interface ContractAgreementCoverMapper extends BaseMapper<ContractAgreementCover> {
}