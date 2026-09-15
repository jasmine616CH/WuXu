package module.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import module.goods.entity.goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * 对应goods表
 */
@Mapper
public interface goodsMapper extends BaseMapper<goods> {
}
