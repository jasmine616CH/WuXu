package module.user.service;

import module.user.dto.LogOutDTO;
import module.user.dto.tokenDTO;

import java.util.Map;

/**
 * redis 缓存业务接口
 * 处理令牌管理，用户登出相关操作
 */
public interface redisService {

    /**
     * 生成刷新Token
     * @param username 用户名
     * @return 刷新令牌
     */
    String generateRefreshToken(String username);

    /**
     * 刷新访问令牌
     * @param refreshToken 刷新令牌
     * @return 令牌对（包含accessToken和refreshToken）
     */
    tokenDTO refreshAccessToken(String refreshToken);

    /**
     * 验证刷新令牌是否有效
     * @param refreshToken 刷新令牌
     * @return true-有效，false-无效
     */
    boolean validateRefreshToken(String refreshToken);

    /**
     * 用户登出
     * @param logoutDTO 登出参数
     */
    void logout(LogOutDTO logoutDTO);


    /**
     * 检查accessToken是否有效
     * @param accessToken 访问令牌
     * @return true-有效，false-无效
     */
    boolean validateAccessToken(String accessToken);

}
