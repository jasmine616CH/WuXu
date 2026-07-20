package module.goods.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class recordDTO {

    /**
     *物品名称
     */
    private String name;

    /**
     * 物品ID
     */
    private Integer ID;

    /**
     * 借用人
     */
    private String borrowing;

    /**
     * 当前时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime time;

}
