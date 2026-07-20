package module.goods.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface situationCensusMapper {

    /**
     * 数据使用次数更新
     */
    @Update("update wuxu.servic_condition set borrowingNumber = borrowingNumber + 1 where ID = #{id}")
    int situationCensus(@Param("id") int id);

}
