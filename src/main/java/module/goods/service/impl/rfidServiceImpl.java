package module.goods.service.impl;

import module.goods.dto.recordDTO;
import module.goods.dto.rfidDTO;
import module.goods.mapper.usageRecordMapper;
import module.goods.mapper.rfidMapper;
import module.goods.service.rfidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RFID数据回传业处理接口实现类
 */
@Service
public class rfidServiceImpl implements rfidService {

    @Autowired
    private rfidMapper rfidMapper;
    @Autowired
    private usageRecordMapper usageRecordMapper;

    /**
     * RFID物品新增处理
     */
    @Override
    public void itemAdd(rfidDTO rfidDTO) {
        rfidMapper.itemAdd(rfidDTO);
    }

    /**
     * RFID物品借用处理
     */
    @Override
    public void itemBorrowing(rfidDTO rfidDTO, recordDTO recordDTO) {
        rfidMapper.itemBorrowing(rfidDTO);
        usageRecordMapper.borrow(recordDTO);
    }

    /**
     * RFID物品归还处理
     */
    @Override
    public void itemBack(rfidDTO rfidDTO, recordDTO recordDTO) {
        rfidMapper.itemBack(rfidDTO);
        usageRecordMapper.back(recordDTO);
    }

    /**
     * RFID物品维修处理
     */
    @Override
    public void itemMaintain(rfidDTO rfidDTO) {
        rfidMapper.itemMaintain(rfidDTO);
    }

    /**
     * RFID物品维修处理-归还
     */
    @Override
    public void itemMaintainBack(rfidDTO rfidDTO) {
        rfidMapper.itemMaintainBack(rfidDTO);
    }

    /**
     * RFID物品报废处理
     */
    @Override
    public void itemScrap(rfidDTO rfidDTO) {
        rfidMapper.itemScrap(rfidDTO);
    }
}
