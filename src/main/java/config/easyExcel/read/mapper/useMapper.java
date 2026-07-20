package config.easyExcel.read.mapper;

import config.easyExcel.read.dto.useData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface useMapper {

    /**
     * 物品批量借用
     */
    int batchBorrow(@Param("list")List<useData> list , @Param("time") LocalDateTime time);

    /**
     * 物品批量归还
     */
    int batchBack(@Param("list")List<useData> list);

}
