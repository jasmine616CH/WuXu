package config.easyExcel.read.service;

import config.easyExcel.read.dto.repairData;

import java.util.List;

public interface repairService {

    /**
     * 批量维修物品
     */
    void repairRead(List<repairData> repairData);

}
