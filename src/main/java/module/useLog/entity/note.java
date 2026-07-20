package module.useLog.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class note {

    /**
     * 物品ID
     */
    private int ID;

    /**
     * 物品名称
     */
    private String name;

    /**
     * 学号
     */
    private String userID;

    /**
     * 借用时间
     */
    private LocalDateTime borrowingTime;

    /**
     * 归还时间
     */
    private LocalDateTime backTime;

}
