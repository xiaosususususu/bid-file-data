package cn.zhongzhi.service;

import cn.zhongzhi.domain.ContractAgreementFirst;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 合同协议首页相关信息的服务接口，定义业务逻辑方法
 */
public interface ContractAgreementFirstService extends IService<ContractAgreementFirst> {
    Map<String, Object> organizeContractData(String bidderId);
}