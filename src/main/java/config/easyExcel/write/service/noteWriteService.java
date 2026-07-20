package config.easyExcel.write.service;

import config.easyExcel.write.dto.noteWriteDTO;
import module.useLog.dto.noteDTO;

import java.io.OutputStream;

/**
 * 使用记录excel表展示业务接口
 */
public interface noteWriteService {

    /**
     * 导出使用记录为 Excel 文件
     */
    void noteWrite(OutputStream outputStream , noteWriteDTO noteWriteDTO);

}
