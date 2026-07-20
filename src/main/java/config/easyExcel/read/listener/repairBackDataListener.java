package config.easyExcel.read.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import config.easyExcel.read.dto.repairData;
import config.easyExcel.read.service.repairBackService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class repairBackDataListener implements ReadListener<repairData> {

    /**
     * 每10条存入数据库，然后清理list，方便回收内存
     */
    private static final int BATCH_COUNT = 10;

    /**
     * 缓存的存储
     */
    private List<repairData> repairDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private repairBackService repairBackService;

    public repairBackDataListener(repairBackService repairSBackService){
        this.repairBackService = repairBackService;
    }

    /**
     * 解析每一条数据
     * @param repairData
     * @param analysisContext
     */
    @Override
    public void invoke(repairData repairData, AnalysisContext analysisContext) {
        repairDataList.add(repairData);
        if (repairDataList.size() >= BATCH_COUNT){
            saveData();
            repairDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
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
        repairBackService.repairBackRead(repairDataList);
    }

}
