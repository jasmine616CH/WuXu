package module.useLog.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("usage_record")
public class noteDTO {

    /**
     * 物品ID
     */
    private int ID;

    /**
     * 物品名称（对应 usage_record.goodsName）
     */
    @TableField("goodsName")
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
