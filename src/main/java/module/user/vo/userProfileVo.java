package module.user.vo;

import lombok.Data;

@Data
public class userProfileVo {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 学号/教职工号
     */
    private String ID;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户权限 1-学生 0-管理员
     */
    private Integer permission;



}
