package module.useLog.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Size(max = 10 , message = "字数最大不能超过10个")
    private String name;

    /**
     * 学号
     */
    @Pattern(regexp = "^[0-9]{12}",
            message = "请输入正确的学号")
    private String userID;

    /**
     * 借用时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime borrowingTime;

    /**
     * 归还时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime backTime;

    //分页查询

    /**
     * 每页最大条数
     */
    @Min(value = 1 , message = "每页最小条数为1")
    @Max(value = 100 , message = "每页最大数为100")
    private Integer pageSize;

    /**
     * 页码数
     */
    @Min(value = 1 , message = "页码最小数为1")
    private Integer pageNum;
}
