package module.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 学号(主键）
     */
    @TableId(value = "ID")
    private String ID;

    /**
     * 用户名
     */
    private String username;

    /**
     * 名称
     */
    private String name;

    /**
     * 密码（盐哈希加密后）
     */
    @TableField("password_hash")
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户权限 1-学生 0-管理员
     */
    private Integer permission;

}
