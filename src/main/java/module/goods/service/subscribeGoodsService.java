package module.goods.service;

import module.goods.dto.subscribeGoodsIdDTO;

import java.util.List;

/**
 * 预定物品接口
 */
public interface subscribeGoodsService {

    /**
     * 批量预定物品
     * @param dto 物品id列表
     */
    void subscribeGoods(List<subscribeGoodsIdDTO> dto);

}
