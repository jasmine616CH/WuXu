package module.goods.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class informationQueryDTO {

    /**
     *物品名称
     */
    private final String name;

    /**
     * 物品ID
     */
    private final Integer ID;

    /**
     * 物品状态 0-在库 1-已借出 2-维修中 3-已报废 4-已丢失
     */
    private final Integer state;

    /**
     * 物品特性 0-耐用品 1-消耗品
     */
    private final Integer properties;

    //分页查询

    /**
     * 每页最大条数
     */
    private Integer pageSize;

    /**
     * 页码数
     */
    private Integer pageNum;


}
