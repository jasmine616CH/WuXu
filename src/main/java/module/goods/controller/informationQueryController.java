package module.goods.controller;

import common.result.Effect;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.goods.dto.informationQueryDTO;
import module.goods.entity.goods;
import module.goods.service.informationQueryService;
import module.goods.vo.informationQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 物品情况查询控制器
 */
@Slf4j
@RestController
@RequestMapping("/goods/query")
@RequiredArgsConstructor
public class informationQueryController {

    @Autowired
    private informationQueryService informationQueryService;

    @PostMapping
    public Effect<List<informationQueryVo>> informationQuery(@Valid @RequestBody informationQueryVo vo){

        //1.Vo转DTO
        informationQueryDTO dto =informationQueryDTO.builder()
                .name(vo.getName())
                .ID(vo.getID())
                .state(vo.getState())
                .properties(vo.getProperties())
                .pageNum(vo.getPageNum())
                .pageSize(vo.getPageSize())
                .build();

        //2.调用查询接口
        List<goods> goodsList = informationQueryService.informationQuery(dto);

        //3.DTO转为Vo
        List<informationQueryVo> listVo = goodsList.stream()
                .map(goods -> {
                    informationQueryVo informationQueryVo = new informationQueryVo();
                    BeanUtils.copyProperties(goods , informationQueryVo);
                    return informationQueryVo;

                })
                .collect(Collectors.toList());

        return Effect.success(listVo);

    }

}
