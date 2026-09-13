package module.goods.controller;

import common.result.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.goods.dto.informationQueryDTO;
import module.goods.dto.subscribeGoodsIdDTO;
import module.goods.entity.goods;
import module.goods.service.informationQueryService;
import module.goods.service.subscribeGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物品情况控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/goods/list")
@RequiredArgsConstructor
public class informationQueryController {

    @Autowired
    private informationQueryService informationQueryService;

    @Autowired
    private subscribeGoodsService  subscribeGoodsService;

    /**
     * 物品查询
     * @param dto 查询数据
     * @return 成功返回相关数据
     */
    @GetMapping
    public Result<Map<String, Object>> informationQuery(@Valid informationQueryDTO dto){

        //1.调用查询接口
        List<goods> goodsList = informationQueryService.informationQuery(dto);

        //2.封装为分页结构（与前端 res.data.records 对应）
        Map<String, Object> pageData = new HashMap<>();
        pageData.put("records", goodsList);
        pageData.put("total", goodsList);

        return Result.success(pageData);

    }

    @GetMapping("/subscribe")
    public Result<String> subscribeGoods(List<subscribeGoodsIdDTO> dto){
        subscribeGoodsService.subscribeGoods(dto);
        return Result.success();
    }

}
