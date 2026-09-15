package module.goods.service;

import module.goods.dto.cancelSubscribeDTO;
import module.goods.dto.subscribeGoodsDTO;

import java.util.List;

/**
 * 预定物品接口
 */
public interface subscribeGoodsService {

    /**
     * 批量预定物品
     * @param dto 物品id
     */
    void subscribeGoods(List<subscribeGoodsDTO> dto);

    /**
     * 批量取消预约物品
     * @param dto 物品id
     */
    void cancelSubscribe(List<cancelSubscribeDTO> dto);

}
