package module.user.vo;

import lombok.Data;

import java.util.List;

/**
 * 用户登录返回信息Vo
 */
@Data
public class logInVo {

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 姓名
     */
    private String name;

    /**
     *学号/教职工号
     */
    private String ID;

    /**
     *用户权限
     */
    private Integer permission;

    /**
     * 菜单栏
     */
    private List<menuRouteVo> menuRoute;

}
