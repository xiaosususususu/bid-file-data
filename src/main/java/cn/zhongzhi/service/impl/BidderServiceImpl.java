package cn.zhongzhi.service.impl;

import cn.zhongzhi.domain.Bidder;
import cn.zhongzhi.mapper.BidderMapper;
import cn.zhongzhi.service.BidderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 投标人服务实现类
 */
@Service
public class BidderServiceImpl extends ServiceImpl<BidderMapper, Bidder> implements BidderService {
}