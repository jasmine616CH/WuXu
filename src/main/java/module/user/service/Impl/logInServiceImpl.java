package module.user.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import common.exception.BusinessException;
import common.result.ResultCode;
import common.until.jwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import module.user.dto.LogOutDTO;
import module.user.dto.logInDTO;
import module.user.entity.User;
import module.user.mapper.userMapper;
import module.user.service.aesService;
import module.user.service.logInService;
import module.user.service.redisService;
import module.user.vo.logInVo;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
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

        //1.根据学号/教职工查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID" , logInDTO.getID());

        //2.条件查询用户信息
        queryWrapper.select("userName" , "name" , "permission" , "passwordHash");
        User user = userMapper.selectOne(queryWrapper);

        //3.检验用户是否存在
        if(user == null){
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }

        //4.检验用户密码
        if(!aesService.decrypt(logInDTO.getPasswordHash()).equals(user.getPassword())){
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

    /**
     * 登出
     * @param http 返回登出参数
     */
    @Override
    @Bean
    public SecurityFilterChain logOut(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")                // 登出请求地址
                        .logoutSuccessUrl("/login?logout")   // 登出成功后跳转到登录页，并携带参数
                        .invalidateHttpSession(true)         // 清除 Session
                        .deleteCookies("JSESSIONID")         // 删除浏览器会话 Cookie
                        .permitAll()
                );
        return http.build();
    }
}
