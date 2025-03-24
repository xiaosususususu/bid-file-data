package cn.zhongzhi.mapper;

import cn.zhongzhi.domain.ProjectApproval;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 批复项目 Mapper 接口
 */
@Mapper
public interface ProjectApprovalMapper extends BaseMapper<ProjectApproval> {
}