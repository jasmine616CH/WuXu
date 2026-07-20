package config.easyExcel.write.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import config.easyExcel.write.dto.noteWriteDTO;
import config.easyExcel.write.service.noteWriteService;
import module.useLog.dto.noteDTO;
import module.useLog.service.noteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;

@Service
public class noteWriteServiceImpl implements noteWriteService {

    @Autowired
    private noteService noteService;

    /**
     * 导出使用记录为 Excel 文件
     */
    @Override
    public void noteWrite(OutputStream outputStream , noteWriteDTO noteWriteDTO) {

        //数据处理
        EasyExcel.write(outputStream, noteWriteDTO.class)
                .sheet("使用记录")
                .doWrite( () -> {

                    //1.转换格式
                    noteDTO noteDTO = new noteDTO();
                    BeanUtils.copyProperties(noteWriteDTO , noteDTO);

                    //2.条件分页查询
                    IPage<noteDTO> dto =  noteService.useFind(noteDTO);
                    
                    return dto.getRecords();
                });


    }
}
