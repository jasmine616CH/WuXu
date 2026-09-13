package module.goods.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import common.until.SecurityUtils;
import module.goods.dto.subscribeGoodsIdDTO;
import module.goods.entity.goods;
import module.goods.mapper.subscribeMapper;
import module.goods.service.subscribeGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void subscribeGoods(List<subscribeGoodsIdDTO> dto) {

        //更新数据库
        int row = subscribeMapper.update(null, Wrappers.<goods>lambdaUpdate()
                .eq(goods::getID, dto)
                .eq(goods::getState, 0)
                .set(goods::getState, 5)
                .set(goods::getBorrowing, SecurityUtils.getCurrentID())
                );
        if (row == 0){
            throw new IllegalStateException("数据错误，请刷新");
        }
    }
}
