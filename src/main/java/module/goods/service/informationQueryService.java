package module.goods.service;

import module.goods.dto.informationQueryDTO;
import module.goods.entity.goods;

import java.util.List;

/**
 * 物品情况查询业务接口
 * 实现物品使用情况查询等功能
 */
public interface informationQueryService{

    /**
     * 根据条件查询物品使用情况
     * @param informationQueryDTO 查询条件
     * @return 物品实体类列表
     */
    List<goods> informationQuery(informationQueryDTO informationQueryDTO);

}
