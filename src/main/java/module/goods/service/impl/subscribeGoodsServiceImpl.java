package module.goods.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import common.enums.ItemCondition;
import common.exception.BusinessException;
import common.result.ResultCode;
import common.until.SecurityUtils;
import module.goods.dto.cancelSubscribeDTO;
import module.goods.dto.subscribeGoodsDTO;
import module.goods.entity.Reservation;
import module.goods.entity.goods;
import module.goods.mapper.goodsMapper;
import module.goods.mapper.subscribeMapper;
import module.goods.service.subscribeGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 物品预定实现类
 */
@Service
public class subscribeGoodsServiceImpl implements subscribeGoodsService {

    /** 预约记录状态：有效（与 reservation.status 建表注释一致，也是 activeFlag 生成列取 1 的条件） */
    private static final int RESERVATION_ACTIVE = 0;

    /** 预约记录状态：无效（已取消／已使用／已过期） */
    private static final int RESERVATION_INVALID = 1;

    @Autowired
    private subscribeMapper subscribeMapper;

    @Autowired
    private goodsMapper goodsMapper;

    /**
     * 预定物品
     * @param dto 物品id列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void subscribeGoods(List<subscribeGoodsDTO> dto) {

        //1.校验参数：预约清单不能为空
        if (dto == null || dto.isEmpty()) {
            throw new BusinessException(ResultCode.GOODS_SUBSCRIBE_EMPTY);
        }

        //2.取出物品id并去重，避免同一个物品重复预约
        List<Integer> ids = dto.stream()
                .filter(Objects::nonNull)
                .map(subscribeGoodsDTO::getObjectId)
                .distinct()
                .toList();

        if (ids.isEmpty()) {
            throw new BusinessException(ResultCode.GOODS_SUBSCRIBE_EMPTY);
        }

        //3.先按时间将商品ID分组
        Map<String, List<Integer>> timeGroupMap = dto.stream()
                .collect(Collectors.groupingBy(
                        dto1 -> dto1.getStartTime() + "_" + dto1.getEndTime(),
                        Collectors.mapping(subscribeGoodsDTO::getObjectId, Collectors.toList())
                ));

        //4.按组批量更新物品状态
        for (Map.Entry<String, List<Integer>> entry : timeGroupMap.entrySet()) {
            List<Integer> groupIds = entry.getValue();

            //更新物品状态
            int row = goodsMapper.update(null, Wrappers.<goods>lambdaUpdate()
                    .in(goods::getID, groupIds)
                    .eq(goods::getState, ItemCondition.AVAILABLE.getCode())
                    .set(goods::getState, ItemCondition.SUBSCRIBE.getCode())
                    .set(goods::getBorrowing, SecurityUtils.getCurrentID())
            );

            // 必须全部成功
            if (row != groupIds.size()) {
                throw new BusinessException(ResultCode.GOODS_SUBSCRIBE_CONFLICT);
            }
        }

        //5.记录预约情况
        subscribeMapper.insertBatch(dto);
    }

    /**
     * 批量取消物品预约
     * @param dto 物品id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelSubscribe(List<cancelSubscribeDTO> dto) {

        //1.校验参数，取消清单不能为空
        if (dto == null || dto.isEmpty()){
            throw new BusinessException(ResultCode.GOODS_CANCEL_EMPTY);
        }

        //2.取出物品id并去重
        List<Integer> ids = dto.stream()
                .filter(Objects::nonNull)
                .map(cancelSubscribeDTO::getObjectId)
                .distinct()
                .toList();

        if (ids.isEmpty()) {
            throw new BusinessException(ResultCode.GOODS_CANCEL_EMPTY);
        }

        //3.当前登录人：只能取消自己预约的物品
        String userId = SecurityUtils.getCurrentID();

        //4.把物品从「已预约」改回「在库」，同时清空预约人
        int row = goodsMapper.update(null, Wrappers.<goods>lambdaUpdate()
                .in(goods::getID, ids)
                .eq(goods::getState, ItemCondition.SUBSCRIBE.getCode())
                .eq(goods::getBorrowing, userId)
                .set(goods::getState, ItemCondition.AVAILABLE.getCode())
                .set(goods::getBorrowing, null)
        );

        //必须全部成功
        if (row != ids.size()) {
            throw new BusinessException(ResultCode.GOODS_CANCEL_CONFLICT);
        }

        //5.把对应的预约记录置为无效
        subscribeMapper.update(null, Wrappers.<Reservation>lambdaUpdate()
                .in(Reservation::getObjectId, ids)
                .eq(Reservation::getUserId, userId)
                .eq(Reservation::getStatus, RESERVATION_ACTIVE)
                .set(Reservation::getStatus, RESERVATION_INVALID)
        );

    }
}
