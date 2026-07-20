package module.goods.service.impl;

import common.exception.BusinessException;
import common.result.ResultCode;
import module.goods.mapper.situationCensusMapper;
import module.goods.service.situationCensusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 物品使用情况统计展示业务接口实现类
 */
@Service
@Transactional
public class situationCensusServiceImpl implements situationCensusService {

    @Autowired
    private situationCensusMapper situationCensusMapper;

    /**
     * 物品使用情况统计
     * @param id 物品ID
     */
    @Override
    public void updateCount(int id) {

        int affected = situationCensusMapper.situationCensus(id);
        if (affected == 0){
            throw new BusinessException(ResultCode.GOODS_NOT_FOUND);
        }

    }
}
