package cn.zhongzhi.service.impl;

import cn.zhongzhi.domain.TenderProject;
import cn.zhongzhi.mapper.TenderProjectMapper;
import cn.zhongzhi.service.TenderProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 招投标项目服务实现类
 */
@Service
public class TenderProjectServiceImpl extends ServiceImpl<TenderProjectMapper, TenderProject> implements TenderProjectService {
}