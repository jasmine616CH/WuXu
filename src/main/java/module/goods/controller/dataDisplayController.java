package module.goods.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.goods.dto.dataDisplayDTO;
import module.goods.service.dataDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 物品使用情况统计展示控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/display")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class dataDisplayController {

    @Autowired
    private dataDisplayService dataDisplayService;

    /**
     * 物品使用情况展示
     */
    @GetMapping
    public List<dataDisplayDTO> dataDisplay(){

        return dataDisplayService.dataDisplay();

    }

}
