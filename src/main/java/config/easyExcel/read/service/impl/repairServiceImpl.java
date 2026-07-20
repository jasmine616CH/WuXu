package config.easyExcel.read.service.impl;

import config.easyExcel.read.dto.repairData;
import config.easyExcel.read.mapper.repairMapper;
import config.easyExcel.read.service.repairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class repairServiceImpl implements repairService {

    @Autowired
    private repairMapper repairMapper;

    /**
     * 批量维修物品
     */
    @Transactional(rollbackFor = Exception.class)
    public void repairRead(List<repairData> repairData) {
        if (repairData == null || repairData.isEmpty())
            return;

        repairMapper.batchRepair(repairData , LocalDateTime.now());
    }

}
