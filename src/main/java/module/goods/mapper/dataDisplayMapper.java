package module.goods.mapper;

import module.goods.dto.dataDisplayDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface dataDisplayMapper {

    /**
     * 展示所有物品使用情况
     */
    @Select("select name , borrowingNumber from wuxu.servic_condition order by borrowingNumber desc ")
    List<dataDisplayDTO> findAllItem();

}
