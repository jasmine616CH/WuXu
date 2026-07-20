package config.easyExcel.read.service;

import config.easyExcel.read.dto.useData;

import java.util.List;

/**
 *物品批量使用业务接口
 * 通过读取excel表批量使用物品
 */
public interface borrowService {

    /**
     * 批量借用物品
     */
    void borrowRead(List<useData> useData);

}
