package module.user.service;

import module.user.vo.userProfileVo;

/**
 * 系统用户业务接口
 * 处理用户信息查询、用户状态检验、用户资料获取等操作
 */
public interface userService {

    /**
     * 根据用户ID判断用户是否存在
     * @param ID 用户ID
     * @return 存在返回turn 不存在返回false
     */
    boolean existsByID(String ID);

    /**
     * 获取用户基本信息
     * @param ID 用户ID
     * @return 用户个人资料
     */
    userProfileVo getUserProfile(String ID);

}
