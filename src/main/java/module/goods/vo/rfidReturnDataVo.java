package module.goods.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class rfidReturnDataVo {

    /**
     *物品名称
     */
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
     * 借用人
     */
    private String borrowing;

    /**
     * 当前时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime dateTime;


}
