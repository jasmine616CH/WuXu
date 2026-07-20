package config.easyExcel.read.service;

import config.easyExcel.read.dto.useData;

import java.util.List;

public interface backService {

    /**
     * 批量归还物品
     */
    void backRead(List<useData> useData);

}
