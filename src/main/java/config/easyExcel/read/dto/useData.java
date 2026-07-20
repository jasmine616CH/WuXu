package config.easyExcel.read.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class useData {

    /**
     * 物品ID
     */
    @ExcelProperty("物品ID")
    private Integer ID;

    /**
     * 物品名称
     */
    @ExcelProperty("物品名称")
    private String name;

}
