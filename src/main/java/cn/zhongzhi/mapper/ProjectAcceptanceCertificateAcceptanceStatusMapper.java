package cn.zhongzhi.mapper;

import cn.zhongzhi.domain.ProjectAcceptanceCertificateAcceptanceStatus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工程竣工验收证书验收单位意见相关信息的 Mapper 接口，用于与数据库进行交互
 */
@Mapper
public interface ProjectAcceptanceCertificateAcceptanceStatusMapper extends BaseMapper<ProjectAcceptanceCertificateAcceptanceStatus> {
}