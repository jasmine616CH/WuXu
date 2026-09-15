package module.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import module.goods.dto.subscribeGoodsDTO;
import module.goods.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface subscribeMapper extends BaseMapper<Reservation> {

    /**
     * 批量插入预约记录
     * @param dto 预约记录列表
     */
    void insertBatch(@Param("list")List<subscribeGoodsDTO> dto);

}
