package module.goods.controller;

import common.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.goods.dto.cancelSubscribeDTO;
import module.goods.dto.subscribeGoodsDTO;
import module.goods.service.subscribeGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 预约物品控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/goods/subscribe")
@RequiredArgsConstructor
public class subscribeController {


    @Autowired
    private subscribeGoodsService subscribeGoodsService;

    /**
     * 批量预约物品
     * @param dto 物品预约信息
     * @return 成功返回提示信息
     */
    @PostMapping()
    public Result<String> subscribeGoods(@RequestBody List<subscribeGoodsDTO> dto){
        subscribeGoodsService.subscribeGoods(dto);
        return Result.success("预约成功" , null);
    }

    /**
     * 批量取消预约物品
     * @param dto 取消物品预约信息
     * @return 成功返回相关参数
     */
    @PostMapping("/cancel")
    public Result<String> cancelSubscribeGoods(@RequestBody List<cancelSubscribeDTO> dto){
        subscribeGoodsService.cancelSubscribe(dto);
        return Result.success("取消预约成功", null);
    }

}
