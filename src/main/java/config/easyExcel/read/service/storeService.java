package config.easyExcel.read.service;

import config.easyExcel.read.dto.storeData;

import java.util.List;

/**
 * 物品
 */
public interface storeService {

    /**
     * 新增物品excel表读取
     */
    void storeRead(List<storeData> storeData);

}
