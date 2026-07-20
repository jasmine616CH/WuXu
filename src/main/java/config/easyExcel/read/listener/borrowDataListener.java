package config.easyExcel.read.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import config.easyExcel.read.dto.useData;
import config.easyExcel.read.service.borrowService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class borrowDataListener implements ReadListener<useData> {

    /**
     * 每10条存入数据库，然后清理list，方便回收内存
     */
    private static final int BATCH_COUNT = 10;

    /**
     * 缓存的存储
     */
    private List<useData> useDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private borrowService borrowService;

    public borrowDataListener(borrowService borrowService){
        this.borrowService = borrowService;
    }

    /**
     * 解析每一条数据
     * @param useData
     * @param analysisContext
     */
    @Override
    public void invoke(useData useData, AnalysisContext analysisContext) {
        useDataList.add(useData);
        if (useDataList.size() >= BATCH_COUNT){
            saveData();
            useDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
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

    private void saveData() {
        borrowService.borrowRead(useDataList);
    }
}
