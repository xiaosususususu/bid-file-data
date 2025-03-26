package cn.zhongzhi.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import cn.zhongzhi.domain.BidWinningNoticeFirst;
import cn.zhongzhi.domain.BidWinningNoticeLast;
import cn.zhongzhi.mapper.BidWinningNoticeFirstMapper;
import cn.zhongzhi.mapper.BidWinningNoticeLastMapper;
import cn.zhongzhi.service.BidWinningNoticeLastService;
import cn.zhongzhi.service.IStructuralDataPublicInterface;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 中标标段通知书相关信息的服务实现类，实现具体的业务逻辑
 */
@Service
@Slf4j
public class BidWinningNoticeLastServiceImpl extends ServiceImpl<BidWinningNoticeLastMapper, BidWinningNoticeLast> implements BidWinningNoticeLastService, IStructuralDataPublicInterface {
    @Resource
    private BidWinningNoticeFirstMapper bidWinningNoticeFirstMapper;
    @Resource
    private BidWinningNoticeLastMapper bidWinningNoticeLastMapper;
    @Override
    public void dataOperation(String tableName, String jsonData, Map<String, String> relationColumnData) {
        BidWinningNoticeLast bidWinningNoticeLast = JSONUtil.toBean(jsonData, BidWinningNoticeLast.class);
        bidWinningNoticeLast.setBidWinningNoticeFirstId(relationColumnData.getOrDefault("bidWinningNoticeFirstId", ""));
        bidWinningNoticeLast.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
        bidWinningNoticeLast.setFileId(relationColumnData.getOrDefault("fileId", ""));
        bidWinningNoticeLast.setId(IdUtil.fastUUID());
        bidWinningNoticeLast.getSafetyOfficersList().forEach(item -> item.setId(IdUtil.fastUUID()));
        int count = bidWinningNoticeLastMapper.insertWithSafetyOfficers(bidWinningNoticeLast);
        if (count <= 0) {
            log.error("插入中标通知书相关信息表失败：tableName{}，jsonData：{}", tableName, jsonData);
            throw new RuntimeException("插入中标通知书相关信息表失败");
        }
        relationColumnData.put("bidWinningNoticeLastId", bidWinningNoticeLast.getId());
        //通过判断 relationColumnData 中是否存在 bidWinningNoticeLastId 来决定是否更新 BidWinningNoticeLast 表中的 bidWinningNoticeFirstId 字段
        if (relationColumnData.containsKey("bidWinningNoticeFirstId")) {
            bidWinningNoticeFirstMapper.updateById(BidWinningNoticeFirst.builder().bidWinningNoticeLastId(bidWinningNoticeLast.getId()).build());
        }
    }
}