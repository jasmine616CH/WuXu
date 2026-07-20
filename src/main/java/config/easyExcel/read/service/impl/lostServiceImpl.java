package config.easyExcel.read.service.impl;

import config.easyExcel.read.dto.repairData;
import config.easyExcel.read.mapper.lostMapper;
import config.easyExcel.read.service.lostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class lostServiceImpl implements lostService {

    @Autowired
    private lostMapper lostMapper;

    /**
     * 批量丢失物品
     */
    @Override
    public void lostRead(List<repairData> repairData) {

            if (repairData == null || repairData.isEmpty())
                return;

            lostMapper.batchLost(repairData , LocalDateTime.now());
    }
}
