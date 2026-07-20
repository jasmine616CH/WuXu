package config.easyExcel.read.service;

import config.easyExcel.read.dto.repairData;

import java.util.List;

public interface lostService {

    /**
     * 批量丢失物品
     */
    void lostRead(List<repairData> repairData);

}
