package config.easyExcel.read.service.impl;

import config.easyExcel.read.dto.repairData;
import config.easyExcel.read.mapper.repairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class repairBackServiceImpl implements config.easyExcel.read.service.repairBackService {

    @Autowired
    private repairMapper repairMapper;

    /**
     * 物品维修归还物品
     */
    @Transactional(rollbackFor = Exception.class)
    public void repairBackRead(List<repairData> repairData) {
        if (repairData == null || repairData.isEmpty())
            return;

        repairMapper.batchRepairBack(repairData);
    }


}
