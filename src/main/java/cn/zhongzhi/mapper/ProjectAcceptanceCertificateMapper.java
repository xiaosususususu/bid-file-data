package cn.zhongzhi.mapper;

import cn.zhongzhi.domain.ProjectAcceptanceCertificate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工程竣工验收证书相关信息的 Mapper 接口，用于与数据库进行交互
 */
@Mapper
public interface ProjectAcceptanceCertificateMapper extends BaseMapper<ProjectAcceptanceCertificate> {
}