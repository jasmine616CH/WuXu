package config.easyExcel.write.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class noteWriteDTO {

    /**
     * 物品ID
     */
    @ExcelProperty("物品ID")
    private int ID;

    /**
     * 物品名称
     */
    @ExcelProperty("物品名称")
    private String name;

    /**
     * 学号
     */
    @ExcelProperty("学号")
    private String userID;

    /**
     * 借用时间
     */
    @ExcelProperty("借用时间")
    private LocalDateTime borrowingTime;

    /**
     * 归还时间
     */
    @ExcelProperty("归还时间")
    private LocalDateTime backTime;

}
