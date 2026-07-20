package config.easyExcel.read.mapper;

import config.easyExcel.read.dto.repairData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface repairMapper {

    /**
     * 批量维修物品
     */
    int batchRepair(@Param("list")List<repairData> list , @Param("time")LocalDateTime time);

    /**
     * 物品维修归还物品
     */
    void batchRepairBack(@Param("list") List<repairData> list);

    /**
     * 物品报废excel表读取
     */
    void batchScrap(@Param("list") List<repairData> list , @Param("time")LocalDateTime time);

}
