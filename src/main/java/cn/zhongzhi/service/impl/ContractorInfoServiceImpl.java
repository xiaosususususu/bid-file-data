package cn.zhongzhi.service.impl;

import cn.zhongzhi.domain.ContractorInfo;
import cn.zhongzhi.mapper.ContractorInfoMapper;
import cn.zhongzhi.service.ContractorInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 承包人信息相关信息的服务实现类，实现具体的业务逻辑
 */
@Service
public class ContractorInfoServiceImpl extends ServiceImpl<ContractorInfoMapper, ContractorInfo> implements ContractorInfoService {
}