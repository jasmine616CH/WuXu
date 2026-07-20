package module.useLog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import module.useLog.dto.noteDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface noteMapper extends BaseMapper<noteDTO> {

}
