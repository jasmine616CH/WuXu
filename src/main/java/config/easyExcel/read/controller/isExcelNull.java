package config.easyExcel.read.controller;

import common.exception.BusinessException;
import common.result.Result;
import common.result.ResultCode;
import org.springframework.web.multipart.MultipartFile;

/**
 * 判断excel表是否为空
 */
public class isExcelNull {

    public static Result isExcelNull(MultipartFile file) {

        //1.校验文件是否为空
        if (file == null || file.isEmpty()){
            return Result.error(new BusinessException(ResultCode.EXCEL_IS_NULL));
        }

        //2.校验文件大小
        long maxSize = 50 * 1024 * 1024;
        if (file.getSize() > maxSize){
            return Result.error(new BusinessException(ResultCode.EXCEL_LARGE_SIZE));
        }

        //3.校验文件格式
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null ||
                !(originalFileName.endsWith(".xlsx")) || !(originalFileName.endsWith(".xls"))){
            return Result.error(new BusinessException(ResultCode.EXCEL_FORMAT_INCORRECT));
        }
        return null;

    }
}
