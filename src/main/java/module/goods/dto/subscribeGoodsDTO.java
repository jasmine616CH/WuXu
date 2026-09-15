package module.goods.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class subscribeGoodsDTO {

    /**
     * 雪花id，业务生成
     */
    private Long id;

    /**
     * 物品id
     */
    private int objectId;

    /**
     * 预约人id
     */
    private String userId;

    /**
     * 预约开始时间
     */
    private LocalDateTime startTime;

    /**
     * 预约结束时间
     */
    private LocalDateTime endTime;

    /**
     * 创建时间
     */
    private LocalDateTime creatTime;

}
