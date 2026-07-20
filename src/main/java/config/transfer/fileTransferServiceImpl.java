package config.transfer;

import com.alibaba.excel.EasyExcel;
import config.easyExcel.read.dto.storeData;
import config.easyExcel.read.listener.storeDataListener;
import config.easyExcel.read.service.storeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * 文件传输业务实现类
 */
@Service
public class fileTransferServiceImpl implements fileTransferService {

    @Autowired
    private storeService storeService;

    /**
     * 文件上传
     */
    @Override
    public void upLoad(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream() , storeData.class , new storeDataListener(storeService)).sheet().doRead();
    }

}
