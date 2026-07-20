package module.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
public class eduStudent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 学号
     */
    private String ID;

    /**
     * 姓名
     */
    private String name;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String phone;

}
