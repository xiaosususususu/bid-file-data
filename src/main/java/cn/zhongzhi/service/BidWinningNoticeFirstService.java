package cn.zhongzhi.service;

import cn.zhongzhi.domain.BidWinningNoticeFirst;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 中标通知书相关信息的服务接口，定义业务逻辑方法
 */
public interface BidWinningNoticeFirstService extends IService<BidWinningNoticeFirst> {
    List<Map<String, Object>> organizeBiwWinningData(String bidderId);
}