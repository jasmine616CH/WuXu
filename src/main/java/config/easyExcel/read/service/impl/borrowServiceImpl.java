package config.easyExcel.read.service.impl;

import config.easyExcel.read.dto.useData;
import config.easyExcel.read.mapper.useMapper;
import config.easyExcel.read.service.borrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 *物品批量使用业务接口实现类
 */
@Service
public class borrowServiceImpl implements borrowService {

    @Autowired
    private useMapper useMapper;

    /**
     * 批量借用物品
     */
    @Transactional(rollbackFor = Exception.class)
    public void borrowRead(List<useData> useData) {

        if (useData == null || useData.isEmpty())
            return;

        useMapper.batchBorrow(useData , LocalDateTime.now());
    }

}
