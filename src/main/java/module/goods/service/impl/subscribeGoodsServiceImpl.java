package module.goods.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import common.enums.ItemCondition;
import common.exception.BusinessException;
import common.result.ResultCode;
import common.until.SecurityUtils;
import module.goods.dto.subscribeGoodsIdDTO;
import module.goods.entity.goods;
import module.goods.mapper.subscribeMapper;
import module.goods.service.subscribeGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 物品预定实现类
 */
@Service
public class subscribeGoodsServiceImpl implements subscribeGoodsService {

    @Autowired
    private subscribeMapper subscribeMapper;

    /**
     * 预定物品
     * @param dto 物品id列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void subscribeGoods(List<subscribeGoodsIdDTO> dto) {

        //1.校验参数：预约清单不能为空
        if (dto == null || dto.isEmpty()) {
            throw new BusinessException(ResultCode.GOODS_SUBSCRIBE_EMPTY);
        }

        //2.取出物品id并去重，避免同一个物品重复预约
        List<Integer> ids = dto.stream()
                .filter(item -> item != null)
                .map(subscribeGoodsIdDTO::getId)
                .distinct()
                .toList();

        if (ids.isEmpty()) {
            throw new BusinessException(ResultCode.GOODS_SUBSCRIBE_EMPTY);
        }

        //3.批量预约：只把「在库」的物品改为「已预约」，同时记录预约人
        int row = subscribeMapper.update(null, Wrappers.<goods>lambdaUpdate()
                .in(goods::getID, ids)
                .eq(goods::getState, ItemCondition.AVAILABLE.getCode())
                .set(goods::getState, ItemCondition.SUBSCRIBE.getCode())
                .set(goods::getBorrowing, SecurityUtils.getCurrentID())
        );

        //4.必须全部预约成功，否则回滚并提示前端刷新
        if (row != ids.size()) {
            throw new BusinessException(ResultCode.GOODS_SUBSCRIBE_CONFLICT);
        }
    }
}
