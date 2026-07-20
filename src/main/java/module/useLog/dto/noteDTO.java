package module.useLog.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class noteDTO {

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
