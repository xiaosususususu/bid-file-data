package cn.zhongzhi.mapper;

import cn.zhongzhi.domain.BidderSectionRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 投标人与标段关系 Mapper 接口
 */
@Mapper
public interface BidderSectionRelationMapper extends BaseMapper<BidderSectionRelation> {
}