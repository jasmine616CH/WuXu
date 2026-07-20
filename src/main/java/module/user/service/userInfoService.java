package module.user.service;

import module.user.dto.userInfoDTO;
import module.user.dto.userRegisterDTO;
import module.user.vo.userInfoVo;
import module.user.vo.userProfileVo;

/**
 *用户信息业务服务接口
 * 实现用户资料查询、修改资料等功能
 */
public interface userInfoService {

    /**
     * 获取当前登录用户基本信息
     *
     * @return 返回用户基本信息
     */
    userInfoVo getUserInfo();

    /**
     * 获取当前登录用户扩展信息
     * @return 用户扩展信息
     */
    userProfileVo getUserProfile();

    /**
     *修改用户密码
     * @param userInfoDTO 用户信息请求体
     */
    void updatePassword(userInfoDTO userInfoDTO);

    /**
     * 用户基本信息修改
     * @param userInfoDTO 用户信息参数
     */
    void updateUserInfo(userInfoDTO userInfoDTO);



}
