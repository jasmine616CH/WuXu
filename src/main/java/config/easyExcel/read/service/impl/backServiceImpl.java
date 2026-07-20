package config.easyExcel.read.service.impl;

import config.easyExcel.read.dto.useData;
import config.easyExcel.read.mapper.useMapper;
import config.easyExcel.read.service.backService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class backServiceImpl implements backService {

    @Autowired
    private useMapper useMapper;

    /**
     * 批量归还物品
     */
    @Transactional(rollbackFor = Exception.class)
    public void backRead(List<useData> useData) {

        if (useData == null || useData.isEmpty())
            return;

        useMapper.batchBack(useData);

    }


}
