package module.user.service.Impl;

import common.exception.BusinessException;
import common.result.ResultCode;
import common.until.jwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import module.user.dto.logInDTO;
import module.user.entity.User;
import module.user.mapper.userMapper;
import module.user.service.aesService;
import module.user.service.logInService;
import module.user.service.redisService;
import module.user.vo.logInVo;
import org.springframework.stereotype.Service;

/**
 * logIn实现类
 */
@RequiredArgsConstructor
@Service
public class logInServiceImpl implements logInService {

    private final userMapper userMapper;
    private final aesService aesService;
    private final jwtTokenProvider jwtTokenProvider;
    private final redisService redisService;

    @SneakyThrows
    @Override
    public logInVo logIn(logInDTO logInDTO) {

        //1.根据学号/教职工查询（selectById 使用实体 @TableField 映射，password_hash 正确映射到 password）
        User user = userMapper.selectById(logInDTO.getID());

        //2.检验用户是否存在
        if(user == null){
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }

        //4.检验用户密码（数据库存的是AES密文，前端传的是明文base64，需解密数据库值后比较）
        if(!aesService.decrypt(user.getPassword()).equals(logInDTO.getPasswordHash())){
            throw new BusinessException(ResultCode.PASSWORD_ERROR);
        }

        //5.生成Token
        String accessToken = jwtTokenProvider.generateAccessToken(user.getID() , user.getUsername() , user.getPermission());
        String refreshToken = redisService.generateRefreshToken(user.getID());

        //6.封装登录信息
        logInVo logInVo = new logInVo();
        logInVo.setAccessToken(accessToken);
        logInVo.setRefreshToken(refreshToken);
        logInVo.setID(user.getID());
        logInVo.setName(user.getName());
        logInVo.setPermission(user.getPermission());

        return logInVo;
    }

}
