package module.goods.service;


/**
 * 物品使用情况统计业务接口
 * 实现物品使用次数统计、物品使用情况展示等功能
 */
public interface situationCensusService {

    /**
     * 物品使用次数统计
     * @param id 物品ID
     */
    void updateCount(int id);

}
