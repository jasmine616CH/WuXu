package module.goods.mapper;

import module.goods.dto.rfidDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface rfidMapper{

    /**
     * RFID物品新增处理
     */
    @Insert("insert into wuxu.object (name, ID, state, properties) " +
            "VALUES (#{name}, #{ID} , #{state} , #{properties} )")
    void itemAdd(rfidDTO rfidDTO);

    /**
     * RFID物品借用处理
     */
    @Update("update wuxu.object set state = #{state} , time = #{time} , borrowing = #{borrowing} where ID = #{ID}")
    void itemBorrowing(rfidDTO rfidDTO);

    /**
     * RFID物品归还处理
     */
    @Update("update wuxu.object set state = #{state} , time = #{time} , borrowing = #{borrowing} where ID = #{ID}")
    void itemBack(rfidDTO rfidDTO);

    /**
     * RFID物品维修处理
     */
    @Update("update wuxu.object set state = 2 where ID = #{ID}")
    void itemMaintain(rfidDTO rfidDTO);

    /**
     * RFID物品维修处理-归还
     */
    @Update("update wuxu.object set state = 0 where ID = #{ID}")
    void itemMaintainBack(rfidDTO rfidDTO);

    /**
     * RFID物品报废处理
     */
    @Update("update wuxu.object set state = 3 where ID = #{ID}")
    void itemScrap(rfidDTO rfidDTO);

}
