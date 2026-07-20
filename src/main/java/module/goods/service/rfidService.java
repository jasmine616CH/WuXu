package module.goods.service;

import module.goods.dto.recordDTO;
import module.goods.dto.rfidDTO;

/**
 * RFID数据回传业处理接口
 * 处理RFID回传的信息
 */
public interface rfidService {

    /**
     * RFID物品新增处理
     */
    void itemAdd(rfidDTO rfidDTO);

    /**
     * RFID物品借用处理
     */
    void itemBorrowing(rfidDTO rfidDTO, recordDTO recordDTO);

    /**
     * RFID物品归还处理
     */
    void itemBack(rfidDTO rfidDTO, recordDTO recordDTO);

    /**
     * RFID物品维修处理
     */
    void itemMaintain(rfidDTO rfidDTO);

    /**
     * RFID物品维修处理-归还
     */
    void itemMaintainBack(rfidDTO rfidDTO);

    /**
     * RFID物品报废处理
     */
    void itemScrap(rfidDTO rfidDTO);

}
