package config.easyExcel.read.service.impl;

import config.easyExcel.read.dto.repairData;
import config.easyExcel.read.mapper.repairMapper;
import config.easyExcel.read.service.scrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class scrapServiceImpl implements scrapService {

    @Autowired
    private repairMapper repairMapper;

    /**
     * 物品报废excel表读取
     */
    @Transactional(rollbackFor = Exception.class)
    public void scrapRead(List<repairData> repairData) {

        if (repairData == null || repairData.isEmpty())
            return;

        repairMapper.batchScrap(repairData , LocalDateTime.now());

    }
}
