package config.easyExcel.read.service;

import config.easyExcel.read.dto.repairData;

import java.util.List;

public interface repairBackService {

    /**
     * 物品维修归还物品
     */
    void repairBackRead(List<repairData> repairData);


}
