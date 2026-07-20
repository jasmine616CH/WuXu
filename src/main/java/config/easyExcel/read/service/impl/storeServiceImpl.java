package config.easyExcel.read.service.impl;

import config.easyExcel.read.dto.storeData;
import config.easyExcel.read.mapper.storeMapper;
import config.easyExcel.read.service.storeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class storeServiceImpl implements storeService {

    @Autowired
    private storeMapper storeMapper;

    /**
     * 新增物品excel表读取
     */
    @Transactional(rollbackFor = Exception.class)
    public void storeRead(List<storeData> storeData) {

        if (storeData == null || storeData.isEmpty())
            return;

        storeMapper.batchInsert(storeData);

    }
}
