package module.user.service;

import module.user.dto.LogOutDTO;

import java.util.Map;

/**
 * redis 缓存业务接口
 * 处理令牌管理，用户登出相关操作
 */
public interface redisService {

    /**
     * 生成刷新Token
     * @param ID 用户ID
     * @return 刷新令牌
     */
    String generateRefreshToken(String ID);

    /**
     * 刷新访问令牌
     * @param refreshToken 刷新令牌
     * @return  令牌对
     */
    Map<String , String> refreshAccessToken(String refreshToken);

    /**
     * 用户登出
     * @param logOutDTO 登出参数
     */
    void logOut(LogOutDTO logOutDTO);

}
