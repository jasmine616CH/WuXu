package config.easyExcel.write.controller;

import config.easyExcel.write.dto.noteWriteDTO;
import config.easyExcel.write.service.noteWriteService;
import config.easyExcel.write.vo.noteWriteVo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * 使用记录excel表展示业务控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/note/find")
@RequiredArgsConstructor
public class noteWriteController {

    @Autowired
    private noteWriteService noteWriteService;

    /**
     * 导出使用记录为 Excel 文件
     */
    @GetMapping("/download")
    public void exportNote(HttpServletResponse response, noteWriteVo noteWriteVo) throws IOException {

        //1.vo转dto
        noteWriteDTO noteWriteDTO = new noteWriteDTO();
        BeanUtils.copyProperties(noteWriteVo , noteWriteDTO);

        //2.响应头设置（完全照搬您 downLoad 的写法，但文件名由前端传入或固定）
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("使用记录", "UTF-8").replace("+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        //3.调用 Service 导出，传入输出流和查询条件
        noteWriteService.noteWrite(response.getOutputStream(), noteWriteDTO);

    }

}
