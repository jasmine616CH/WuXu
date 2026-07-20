package module.useLog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import module.useLog.dto.noteDTO;

/**
 * 使用记录查询业务接口
 */
public interface noteService {

    /**
     * 查询物品使用情况
     */
    IPage<noteDTO> useFind(noteDTO noteDTO);

}
