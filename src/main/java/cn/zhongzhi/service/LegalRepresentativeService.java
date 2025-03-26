package cn.zhongzhi.service;


import cn.zhongzhi.domain.LegalRepresentative;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author suchengbo
 * @Date 2025/3/21 17:26
 */
public interface LegalRepresentativeService extends IService<LegalRepresentative> {
    Map<String, Object> organizeIdentityData(String bidderId);
}
