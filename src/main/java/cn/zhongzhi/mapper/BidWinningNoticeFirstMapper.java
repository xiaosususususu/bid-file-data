package cn.zhongzhi.mapper;

import cn.zhongzhi.domain.BidWinningNoticeFirst;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 中标通知书相关信息的 Mapper 接口，用于与数据库进行交互
 */
@Mapper
public interface BidWinningNoticeFirstMapper extends BaseMapper<BidWinningNoticeFirst> {

    /**
     * 一对多新增中标通知书及关联的施工员信息
     * @param bidWinningNoticeFirst 中标通知书信息
     * @return 影响的行数
     */
    int insertWithConstructionCrews(BidWinningNoticeFirst bidWinningNoticeFirst);
}