package config.easyExcel.read.mapper;

import config.easyExcel.read.dto.storeData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface storeMapper {

    /**
     * 批量插入数据
     */
    int batchInsert(@Param("list")List<storeData> list);

}
