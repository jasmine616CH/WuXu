package module.user.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import common.exception.BusinessException;
import common.result.ResultCode;
import common.until.jwtTokenProvider;
import lombok.RequiredArgsConstructor;
import module.user.dto.LogOutDTO;
import module.user.entity.User;
import module.user.mapper.userMapper;
import module.user.service.redisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * redis实现类
 */
@Service
@RequiredArgsConstructor
public class redisServiceImpl implements redisService {

    private final jwtTokenProvider jwtTokenProvider;
    private final StringRedisTemplate stringRedisTemplate;
    private final userMapper userMapper;

    @Value("${token.refresh-token-ttl}")
    private long refreshTokenTtl;

    //redis key 前缀
    private static final String USER_RT_PREFIX = "refresh-token:user:";
    private static final String RT_PREFIX = "refresh-token:rt:";

    /**
     *生成刷新令牌
     * @param ID 用户ID
     * @return 新令牌
     */
    @Override
    public String generateRefreshToken(String ID) {

        //1.删除旧的refreshToken
        String oldRefreshToken = stringRedisTemplate.opsForValue().get(USER_RT_PREFIX + ID);
        if (oldRefreshToken != null){
            stringRedisTemplate.delete(RT_PREFIX + oldRefreshToken);
        }

        //2.生成新的refreshToken
        String refreshToken = UUID.randomUUID().toString();

        //3.保存双向映射
        stringRedisTemplate.opsForValue()
                .set(RT_PREFIX + refreshToken , String.valueOf(ID) , refreshTokenTtl , TimeUnit.HOURS);
        stringRedisTemplate.opsForValue()
                .set(RT_PREFIX + ID , refreshToken , refreshTokenTtl , TimeUnit.HOURS);

        return refreshToken;
    }

    /**
     * 使用 refreshToken 刷新 accessToken
     * @param refreshToken 刷新令牌
     */
    @Override
    public Map<String, String> refreshAccessToken(String refreshToken) {

        //1.检验令牌是否有效
        String ID = stringRedisTemplate.opsForValue().get(RT_PREFIX + refreshToken);
        if (ID == null){
            throw new BusinessException(ResultCode.TOKEN_INVALID);
        }

        //2.查询用户信息
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getID , ID)
                .select(User::getID , User::getUsername , User::getPassword);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }

        //3.生成新的accessToken
        String newAccessToken = jwtTokenProvider.generateAccessToken(user.getID() , user.getUsername() , user.getPermission());

        //4.删除旧的token 生成新的token
        revokeRefreshToken(ID , refreshToken);
        String newRefreshToken = generateRefreshToken(user.getID());

        //5.返回令牌
        Map<String , String> map = new HashMap<>();
        map.put("refreshToken" , newRefreshToken);
        map.put("accessToken" , newAccessToken);
        return map;
    }

    /**
     * 登出
     * @param logOutDTO 登出参数
     */
    @Override
    public void logOut(LogOutDTO logOutDTO) {
        //撤销刷新令牌
        revokeRefreshToken(logOutDTO.getUserID() , logOutDTO.getRefreshToken());

    }

    /**
     * 主动撤销token
     */
    public void revokeRefreshToken(String ID , String refreshToken){
        stringRedisTemplate.delete(USER_RT_PREFIX + ID);
        stringRedisTemplate.delete(RT_PREFIX + refreshToken);
    }

}
