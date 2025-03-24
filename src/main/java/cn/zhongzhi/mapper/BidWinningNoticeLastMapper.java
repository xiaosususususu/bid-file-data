package cn.zhongzhi.mapper;

import cn.zhongzhi.domain.BidWinningNoticeLast;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 中标标段通知书相关信息的 Mapper 接口，用于与数据库进行交互
 */
@Mapper
public interface BidWinningNoticeLastMapper extends BaseMapper<BidWinningNoticeLast> {
}