package cn.zhongzhi.service.impl;

import cn.zhongzhi.domain.ClientInfo;
import cn.zhongzhi.mapper.ClientInfoMapper;
import cn.zhongzhi.service.ClientInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 发包人信息相关信息的服务实现类，实现具体的业务逻辑
 */
@Service
public class ClientInfoServiceImpl extends ServiceImpl<ClientInfoMapper, ClientInfo> implements ClientInfoService {
}