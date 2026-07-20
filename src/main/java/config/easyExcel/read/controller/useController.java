package config.easyExcel.read.controller;

import com.alibaba.excel.EasyExcel;
import common.result.Result;
import config.easyExcel.read.dto.storeData;
import config.easyExcel.read.listener.backDataListener;
import config.easyExcel.read.listener.borrowDataListener;
import config.easyExcel.read.service.backService;
import config.easyExcel.read.service.borrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/use")
public class useController {

    @Autowired
    private borrowService borrowService;
    private backService backService;

    /**
     * 借用物品excel表读取
     */
    @PostMapping("/borrow/upload")
    public Result borrowUpload(@RequestParam("file") MultipartFile file){
        //1.校验文件是否为空
        Result EXCEL_IS_NULL = isExcelNull.isExcelNull(file);
        if (EXCEL_IS_NULL != null) return EXCEL_IS_NULL;

        //2.解析excel表
        try {

            borrowDataListener borrowDataListener = new borrowDataListener(borrowService);
            EasyExcel.read(file.getInputStream() , storeData.class , borrowDataListener)
                    .sheet()
                    .doRead();
            return Result.success();

        } catch (IOException e){
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 归还物品excel表读取
     */
    @PostMapping("/back/upload")
    public Result backUpload(@RequestParam("file") MultipartFile file){
        //1.校验文件是否为空
        Result EXCEL_IS_NULL = isExcelNull.isExcelNull(file);
        if (EXCEL_IS_NULL != null) return EXCEL_IS_NULL;

        //2.解析excel表
        try {

            backDataListener backDataListener = new backDataListener(backService);
            EasyExcel.read(file.getInputStream() , storeData.class , backDataListener)
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
