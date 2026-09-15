package module.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 物品预约实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("reservation")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 雪花ID (业务生成)
     */
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 预约物品编号，关联 object.ID
     */
    @TableField("objectID")
    private int objectId;

    /**
     * 预约人学号或教职工号，关联 user.ID
     * 注：学号/工号通常包含字母或前导零，建议使用 String 类型
     */
    @TableField("userID")
    private String userId;

    /**
     * 预约时间段开始时间
     */
    @TableField("startTime")
    private LocalDateTime startTime;

    /**
     * 预约时间段结束时间，由应用层保证等于开始时间加 2 小时
     */
    @TableField("endTime")
    private LocalDateTime endTime;

    /**
     * 预约创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 预约状态 0-有效 1-无效 (已使用、已取消、已过期均置为 1)
     */
    @TableField("status")
    private Integer status;

    /**
     * 有效标记 有效-1 无效-NULL，仅用于唯一索引，不参与业务
     */
    @TableField("activeFlag")
    private Integer activeFlag;
}