package module.user.dto;

import lombok.Data;

@Data
public class userRegisterDTO {

    /**
     * 学号/教职工号
     */
    private String ID;

    /**
     * 用户名
     */
     private String userName;

    /**
     * 密码-加密
     */
    private  String password_hash;


    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
     private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户权限 0-教师 1学生
     */
     private Integer permission;

}
