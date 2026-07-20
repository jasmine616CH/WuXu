package config.easyExcel.read.controller;

import com.alibaba.excel.EasyExcel;
import common.result.Result;
import config.easyExcel.read.dto.repairData;
import config.easyExcel.read.listener.lostDataListener;
import config.easyExcel.read.service.lostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 丢失物品excel表读取处理器
 */
@RestController
@RequestMapping("/api/lost")
public class lostController {

    @Autowired
    private lostService lostService;

    /**
     * 维修物品excel表读取
     */
    @PostMapping("/upload")
    public Result lostUpload(@RequestParam("file") MultipartFile file){

        //1.校验文件是否为空
        Result EXCEL_IS_NULL = isExcelNull.isExcelNull(file);
        if (EXCEL_IS_NULL != null) return EXCEL_IS_NULL;

        //2.解析excel表
        try {

            lostDataListener lostDataListener = new lostDataListener(lostService);
            EasyExcel.read(file.getInputStream() , repairData.class , lostDataListener)
                    .sheet()
                    .doRead();
            return Result.success();

        } catch (IOException e){
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
