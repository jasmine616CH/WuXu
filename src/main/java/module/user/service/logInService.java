package module.user.service;

import module.user.dto.LogOutDTO;
import module.user.dto.logInDTO;
import module.user.vo.logInVo;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 系统登录业务接口
 * 实现用户的登录和登出功能
 */
public interface logInService {

    /**
     * 登录
     * @param logInDTO 登录请求参数 - 账号和密码
     * @return 登录成功返回用户信息和令牌
     */
    logInVo logIn(logInDTO logInDTO);

    /**
     * 登出
     * @param http 登出系统参数
     */
    SecurityFilterChain logOut(HttpSecurity http) throws Exception;

}
