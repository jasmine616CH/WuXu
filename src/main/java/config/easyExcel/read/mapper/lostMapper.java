package config.easyExcel.read.mapper;

import config.easyExcel.read.dto.repairData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface lostMapper {

    /**
     * 物品丢失excel表读取
     */
    int batchLost(@Param("list") List<repairData> list , @Param("time") LocalDateTime time);

}
