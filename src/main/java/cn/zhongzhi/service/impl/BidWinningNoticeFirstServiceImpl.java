package cn.zhongzhi.service.impl;

import cn.zhongzhi.domain.BidWinningNoticeFirst;
import cn.zhongzhi.mapper.BidWinningNoticeFirstMapper;
import cn.zhongzhi.service.BidWinningNoticeFirstService;
import cn.zhongzhi.service.IStructuralDataPublicInterface;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 中标通知书相关信息的服务实现类，实现具体的业务逻辑
 */
@Service
public class BidWinningNoticeFirstServiceImpl extends ServiceImpl<BidWinningNoticeFirstMapper, BidWinningNoticeFirst> implements BidWinningNoticeFirstService, IStructuralDataPublicInterface {
    @Override
    public void dataOperation(String tableName, String jsonData, Map<String, String> relationColumnData) {

    }
}