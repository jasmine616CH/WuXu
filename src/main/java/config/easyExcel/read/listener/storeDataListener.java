package config.easyExcel.read.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import config.easyExcel.read.dto.storeData;
import config.easyExcel.read.service.storeService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * excel表监听器
 */
@Slf4j
public class storeDataListener implements ReadListener<storeData> {

    /**
     * 每10条存入数据库，然后清理list，方便回收内存
     */
    private static final int BATCH_COUNT = 10;

    /**
     * 缓存的存储
     */
    private List<storeData> storeDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private storeService storeService;

    public storeDataListener(storeService storeService){
        this.storeService = storeService;
    }

    /**
     * 解析每一条数据
     * @param storeData
     * @param analysisContext
     */
    @Override
    public void invoke(storeData storeData, AnalysisContext analysisContext) {
        storeDataList.add(storeData);
        if (storeDataList.size() >= BATCH_COUNT){
            saveData();
            storeDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 解析所有数据后保存
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
    }

    /**
     * 加上存储数据库
     */
    public void saveData(){
        storeService.storeRead(storeDataList);
    }

}
