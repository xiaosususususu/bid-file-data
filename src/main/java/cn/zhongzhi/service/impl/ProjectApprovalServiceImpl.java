package cn.zhongzhi.service.impl;

import cn.zhongzhi.domain.ProjectApproval;
import cn.zhongzhi.mapper.ProjectApprovalMapper;
import cn.zhongzhi.service.ProjectApprovalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 批复项目服务实现类
 */
@Service
public class ProjectApprovalServiceImpl extends ServiceImpl<ProjectApprovalMapper, ProjectApproval> implements ProjectApprovalService {
}