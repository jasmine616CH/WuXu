package module.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("object")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class goods {

    /**
     *物品名称
     */
    private String name;

    /**
     * 物品ID
     */
    private Integer ID;

    /**
     * 物品状态 0-在库 1-已借出 2-维修中 3-已报废 4-已丢失 5-已预约
     */
    private Integer state;

    /**
     * 借用人
     */
    private String borrowing;

    /**
     * 物品特性 0-耐用品 1-消耗品
     */
    private Integer properties;

}
