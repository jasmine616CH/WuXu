package config.transfer;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件传输业务接口
 * 处理文件上传和文件下载等功能
 */
public interface fileTransferService {

    /**
     * 文件上传
     */
    public void upLoad(MultipartFile file) throws IOException;

}
