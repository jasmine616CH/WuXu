package module.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class userRegisterDTO {

    /**
     * 学号/教职工号
     */
    @NotBlank(message = "学号不能为空")
    private String ID;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
     private String userName;

    /**
     * 密码-加密
     */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[A-Za-z0-9+/=]{4,64}",
            message = "密码格式不对")
    private  String passwordHash;


    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$" , message = "手机号格式不正确，请输入正确的格式")
    private final String phone;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "^[A-Za-z0-9._-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-])*+\\.[A-Za-z0-9-]{2,6}$",
            message = "邮箱格式不正确，请输入正确格式")
    private String email;

    /**
     * 用户权限 0-教师 1学生
     */
     private Integer permission;

}
