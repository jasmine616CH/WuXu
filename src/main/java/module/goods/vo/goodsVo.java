package module.goods.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class goodsVo {

    /**
     *物品名称
     */
    @NotBlank(message = "物品名称不能为空")
    private final String name;

    /**
     * 物品ID
     */
    @NotBlank(message = "物品ID不能为空")
    private final Integer ID;

    /**
     * 物品状态 0-在库 1-已借出 2-维修中 3-已报废 4-已丢失
     */
    private final Integer state;

    /**
     * 借用人
     */
    private String borrowing;

    /**
     * 物品特性 0-耐用品 1-消耗品
     */
    private final Integer properties;

}
