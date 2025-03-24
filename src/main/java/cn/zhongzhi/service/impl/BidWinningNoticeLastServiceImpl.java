package cn.zhongzhi.service.impl;

import cn.zhongzhi.domain.BidWinningNoticeLast;
import cn.zhongzhi.mapper.BidWinningNoticeLastMapper;
import cn.zhongzhi.service.BidWinningNoticeLastService;
import cn.zhongzhi.service.IStructuralDataPublicInterface;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 中标标段通知书相关信息的服务实现类，实现具体的业务逻辑
 */
@Service
public class BidWinningNoticeLastServiceImpl extends ServiceImpl<BidWinningNoticeLastMapper, BidWinningNoticeLast> implements BidWinningNoticeLastService, IStructuralDataPublicInterface {
    @Override
    public void dataOperation(String tableName, String jsonData, Map<String, String> relationColumnData) {

    }
}