package cn.zhongzhi.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import cn.zhongzhi.domain.BidWinningNoticeConstructionCrew;
import cn.zhongzhi.domain.BidWinningNoticeFirst;
import cn.zhongzhi.domain.BidWinningNoticeLast;
import cn.zhongzhi.domain.BidWinningNoticeSafetyOfficers;
import cn.zhongzhi.mapper.BidWinningNoticeConstructionCrewMapper;
import cn.zhongzhi.mapper.BidWinningNoticeFirstMapper;
import cn.zhongzhi.mapper.BidWinningNoticeLastMapper;
import cn.zhongzhi.mapper.BidWinningNoticeSafetyOfficersMapper;
import cn.zhongzhi.service.BidWinningNoticeFirstService;
import cn.zhongzhi.service.IStructuralDataPublicInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 中标通知书相关信息的服务实现类，实现具体的业务逻辑
 */
@Service
@Slf4j
public class BidWinningNoticeFirstServiceImpl extends ServiceImpl<BidWinningNoticeFirstMapper, BidWinningNoticeFirst> implements BidWinningNoticeFirstService, IStructuralDataPublicInterface {
    @Resource
    private BidWinningNoticeFirstMapper bidWinningNoticeFirstMapper;
    @Resource
    private BidWinningNoticeConstructionCrewMapper bidWinningNoticeConstructionCrewMapper;
    @Resource
    private BidWinningNoticeLastMapper bidWinningNoticeLastMapper;
    @Resource
    private BidWinningNoticeSafetyOfficersMapper bidWinningNoticeSafetyOfficersMapper;
    @Override
    public void dataOperation(String tableName, String jsonData, Map<String, String> relationColumnData) {
        BidWinningNoticeFirst bidWinningNoticeFirst = JSONUtil.toBean(jsonData, BidWinningNoticeFirst.class);
        bidWinningNoticeFirst.setBidWinningNoticeLastId(relationColumnData.getOrDefault("bidWinningNoticeLastId", ""));
        bidWinningNoticeFirst.setBidderId(relationColumnData.getOrDefault("bidderId", ""));
        bidWinningNoticeFirst.setFileId(relationColumnData.getOrDefault("fileId", ""));
        bidWinningNoticeFirst.setId(IdUtil.fastUUID());
        bidWinningNoticeFirst.getConstructionCrews().forEach(item -> item.setId(IdUtil.fastUUID()));
        int count = bidWinningNoticeFirstMapper.insertWithConstructionCrews(bidWinningNoticeFirst);
        if (count <= 0) {
            log.error("插入中标通知书相关信息表失败：tableName{}，jsonData：{}", tableName, jsonData);
            throw new RuntimeException("插入中标通知书相关信息表失败");
        }
        relationColumnData.put("bidWinningNoticeFirstId", bidWinningNoticeFirst.getId());
        //通过判断 relationColumnData 中是否存在 bidWinningNoticeLastId 来决定是否更新 BidWinningNoticeLast 表中的 bidWinningNoticeFirstId 字段
        if (relationColumnData.containsKey("bidWinningNoticeLastId")) {
            bidWinningNoticeLastMapper.updateById(BidWinningNoticeLast.builder().bidWinningNoticeFirstId(bidWinningNoticeFirst.getId()).build());
        }
    }

    @Override
    public List<Map<String, Object>> organizeBiwWinningData(String bidderId) {
        List<Map<String, Object>> resultListMap = new ArrayList<>();
        List<BidWinningNoticeLast> bidWinningNoticeLastList = bidWinningNoticeLastMapper.selectList(new QueryWrapper<BidWinningNoticeLast>().eq("bidder_id", bidderId));
        for (BidWinningNoticeLast bidWinningNoticeLast : bidWinningNoticeLastList) {
            List<BidWinningNoticeSafetyOfficers> bidWinningNoticeSafetyOfficersList = bidWinningNoticeSafetyOfficersMapper.selectList(new QueryWrapper<BidWinningNoticeSafetyOfficers>().eq("bid_winning_notice_last_id", bidWinningNoticeLast.getId()));
            bidWinningNoticeLast.setSafetyOfficersList(bidWinningNoticeSafetyOfficersList);
            BidWinningNoticeFirst bidWinningNoticeFirst = bidWinningNoticeFirstMapper.selectOne(new QueryWrapper<BidWinningNoticeFirst>().eq("id", bidWinningNoticeLast.getBidWinningNoticeFirstId()));
            List<BidWinningNoticeConstructionCrew> bidWinningNoticeConstructionCrewList = bidWinningNoticeConstructionCrewMapper.selectList(new QueryWrapper<BidWinningNoticeConstructionCrew>().eq("bid_winning_notice_first_id", bidWinningNoticeFirst.getId()));
            bidWinningNoticeFirst.setConstructionCrews(bidWinningNoticeConstructionCrewList);
            Map<String, Object> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("bidWinningNoticeFirst", bidWinningNoticeFirst);
            linkedHashMap.put("bidWinningNoticeLast", bidWinningNoticeLast);
            resultListMap.add(linkedHashMap);
        }
        return resultListMap;
    }
}