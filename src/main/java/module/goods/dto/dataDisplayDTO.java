package module.goods.dto;

import lombok.Data;

/**
 * 物品使用情况展示数据
 */
@Data
public class dataDisplayDTO {

    /**
     * 使用次数
     */
    private int usageCount;

    /**
     * 物品名称
     */
    private String name;

    public dataDisplayDTO(int usageCount , String name){
        this.name = name;
        this.usageCount = usageCount;
    }

}
