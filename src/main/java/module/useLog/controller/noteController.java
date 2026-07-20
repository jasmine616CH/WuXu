package module.useLog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.useLog.dto.noteDTO;
import module.useLog.service.noteService;
import module.useLog.vo.noteVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/note/find")
@RequiredArgsConstructor
public class noteController {

    @Autowired
    private noteService noteService;

    /**
     * 使用情况查询
     */
    @GetMapping
    public IPage<noteVo> noteFind(noteVo noteVo){

        //1.Vo转DTO
        noteDTO noteDTO = new noteDTO();
        BeanUtils.copyProperties(noteVo, noteDTO);

        //2.处理数据
        IPage<noteDTO> iPage = noteService.useFind(noteDTO);

        return iPage.convert(noteDTO1 -> {

            noteVo vo = new noteVo();
            BeanUtils.copyProperties(noteDTO1 , vo);
            return vo;

        });

    }
}
