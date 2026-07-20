package config.transfer;

import common.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件传输控制器
 */
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class fileTransferController {

    private final fileTransferService fileTransferService;

    /**
     * 文件上传
     */
    @ResponseBody
    @PostMapping("/goods/upload")
    public Result upLoad(MultipartFile file){
        try {
            fileTransferService.upLoad(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Result.success();
    }

}
