package module.goods.vo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class informationQueryVo {

    /**
     *物品名称
     */
    @Size(max = 10 , message = "字数最大不能超过10个")
    private String name;

    /**
     * 物品ID
     */

    private Integer ID;

    /**
     * 物品状态 0-在库 1-已借出 2-维修中 3-已报废 4-已丢失
     */
    private Integer state;

    /**
     * 物品特性 0-耐用品 1-消耗品
     */
    private Integer properties;

    //分页查询

    /**
     * 每页最大条数
     */
    @Min(value = 1 , message = "每页最小条数为1")
    @Max(value = 100 , message = "每页最大数为100")
    private Integer pageSize = 10;

    /**
     * 页码数
     */
    @Min(value = 1 , message = "页码最小数为1")
    private Integer pageNum = 1;


}
