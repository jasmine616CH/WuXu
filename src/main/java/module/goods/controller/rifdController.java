package module.goods.controller;

import config.convert.recordVoToDto;
import config.convert.rfidVoToDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.goods.service.rfidService;
import module.goods.vo.rfidReturnDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * RFID回传信息控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/rfid")
@RequiredArgsConstructor
public class rifdController {

    @Autowired
    private rfidService rfidService;

    /**
     * RFID物品新增处理
     */
    @PostMapping("/add")
    public String itemAdd(@Valid @RequestBody rfidReturnDataVo rfidReturnDataVo){

        //处理数据
        rfidService.itemAdd(rfidVoToDto.VTD(rfidReturnDataVo));

        return  "{\"status\":\"success\"," +
                " \"message\":\"已接收\"}";

    }

    /**
     * RFID物品借用处理
     */
    @PostMapping("/borrowing")
    public String itemBorrowing(@Valid @RequestBody rfidReturnDataVo rfidReturnDataVo){

        //处理数据
        rfidService.itemBorrowing(rfidVoToDto.VTD(rfidReturnDataVo) , recordVoToDto.VTD(rfidReturnDataVo) );

        return  "{\"status\":\"success\"," +
                " \"message\":\"已接收\"}";

    }

    /**
     * RFID物品归还处理
     */
    @PostMapping("/back")
    public String itemBack(@Valid@RequestBody rfidReturnDataVo rfidReturnDataVo){

        //处理数据
        rfidService.itemBack(rfidVoToDto.VTD(rfidReturnDataVo), recordVoToDto.VTD(rfidReturnDataVo));

        return  "{\"status\":\"success\"," +
                " \"message\":\"已接收\"}";

    }

    /**
     * RFID物品维修处理
     */
    @PostMapping("/maintain")
    public String itemMaintain(@Valid@RequestBody rfidReturnDataVo rfidReturnDataVo){

        //处理数据
        rfidService.itemMaintain(rfidVoToDto.VTD(rfidReturnDataVo));

        return  "{\"status\":\"success\"," +
                " \"message\":\"已接收\"}";

    }

    /**
     * RFID物品维修处理-归还
     */
    @PostMapping("/maintain/back")
    public String itemMaintainBack(@Valid@RequestBody rfidReturnDataVo rfidReturnDataVo){

        //处理数据
        rfidService.itemMaintainBack(rfidVoToDto.VTD(rfidReturnDataVo));

        return  "{\"status\":\"success\"," +
                " \"message\":\"已接收\"}";

    }

    /**
     * RFID物品报废处理
     */
    @PostMapping("/scrap")
    public String itemScrap(@Valid@RequestBody rfidReturnDataVo rfidReturnDataVo){

        //处理数据
        rfidService.itemScrap(rfidVoToDto.VTD(rfidReturnDataVo));

        return  "{\"status\":\"success\"," +
                " \"message\":\"已接收\"}";

    }

}
