package module.goods.service;

import module.goods.dto.dataDisplayDTO;

import java.util.List;

/**
 * 物品使用情况统计展示业务接口
 * 实现数据展示等功能
 */
public interface dataDisplayService {

    /**
     * 物品使用情况展示
     *
     * @return 物品使用情况列表
     */
    List<dataDisplayDTO> dataDisplay();

}
