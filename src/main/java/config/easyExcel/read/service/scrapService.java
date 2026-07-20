package config.easyExcel.read.service;

import config.easyExcel.read.dto.repairData;

import java.util.List;

public interface scrapService {

    /**
     * 物品报废excel表读取
     */
    void scrapRead(List<repairData> repairData);

}
