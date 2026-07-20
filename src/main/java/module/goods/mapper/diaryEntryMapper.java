package module.goods.mapper;

import module.goods.dto.recordDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * 日志记录
 */
@Mapper
public interface diaryEntryMapper {

    /**
     * 物品借用
     */
    @Insert("insert into wuxu.usage_record (goodsName, userID, borrowingTime) VALUES (#{name} , #{borrowing} , #{time})")
    void borrow(recordDTO recordDTO);

    /**
     * 物品归还
     */
    @Update("update wuxu.usage_record set backTime = #{backTime} where ID = #{ID}")
    void back(recordDTO recordDTO);

}
