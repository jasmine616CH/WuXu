package config.easyExcel.read.controller;

import com.alibaba.excel.EasyExcel;
import common.result.Result;
import config.easyExcel.read.dto.repairData;
import config.easyExcel.read.dto.storeData;
import config.easyExcel.read.listener.repairBackDataListener;
import config.easyExcel.read.listener.repairDataListener;
import config.easyExcel.read.listener.scrapDataListener;
import config.easyExcel.read.service.repairBackService;
import config.easyExcel.read.service.repairService;
import config.easyExcel.read.service.scrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 维修物品excel表读取处理器
 */
@RestController
@RequestMapping("/api/repair")
public class repairController {

    @Autowired
    private repairService repairService;
    @Autowired
    private repairBackService repairBackService;
    @Autowired
    private scrapService scrapService;

    /**
     * 维修物品excel表读取
     */
    @PostMapping("/upload")
    public Result repairUpload(@RequestParam("file") MultipartFile file){

        Result EXCEL_IS_NULL = isExcelNull.isExcelNull(file);
        if (EXCEL_IS_NULL != null) return EXCEL_IS_NULL;

        //2.解析excel表
        try {

            repairDataListener repairDataListener = new repairDataListener(repairService);
            EasyExcel.read(file.getInputStream() , repairData.class , repairDataListener)
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
     * 维修物品归还excel表读取
     */
    @PostMapping("/back/upload")
    public Result repairBackUpload(@RequestParam("file") MultipartFile file){

        //1.校验文件是否为空
        Result EXCEL_IS_NULL = isExcelNull.isExcelNull(file);
        if (EXCEL_IS_NULL != null) return EXCEL_IS_NULL;

        //2.解析excel表
        try {

            repairBackDataListener repairBackDataListener = new repairBackDataListener(repairBackService);
            EasyExcel.read(file.getInputStream() , storeData.class , repairBackDataListener)
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
     * 物品报废excel表读取
     */
    @PostMapping("/scrap/upload")
    public Result scrapUpload(@RequestParam("file") MultipartFile file){

        //1.校验文件是否为空
        Result EXCEL_IS_NULL = isExcelNull.isExcelNull(file);
        if (EXCEL_IS_NULL != null) return EXCEL_IS_NULL;

        //2.解析excel表
        try {

            scrapDataListener scrapDataListener = new scrapDataListener(scrapService);
            EasyExcel.read(file.getInputStream() , repairData.class , scrapDataListener)
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
