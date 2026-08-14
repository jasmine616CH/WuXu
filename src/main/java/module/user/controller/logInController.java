package module.user.controller;

import common.result.Result;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.user.dto.LogOutDTO;
import module.user.dto.logInDTO;
import module.user.service.logInService;
import module.user.service.redisService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.*;

/**
 * 登录服务实现类
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class logInController {

    private final logInService logInService;
    private final redisService redisService;

    /**
     *用户登录接口
     * @param logInDTO 登录相关参数
     * @return 成功返回相关参数，失败返回错误信息
     */
    @PostMapping("/login")
    public Result logIn(@RequestBody logInDTO logInDTO){
        return Result.success(logInService.logIn(logInDTO));
    }

    /**
     * 用户登出接口
     * @param http 登出相关参数
     * @return  成功返回相关参数，失败返回错误信息
     */
    @PostMapping("logout")
    public Result logOut(HttpSecurity http){
        try {
            logInService.logOut(http);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success();
    }

    /**
     * 刷新token接口
     * @param refreshToken 刷新token
     * @return 成功返回accessToken和refreshToken,失败返回错误信息
     */
    @PostMapping("/refresh-access-token")
    public Result refreshAccessToken(@NotBlank @RequestParam String refreshToken){
        return Result.success(redisService.refreshAccessToken(refreshToken));
    }



}
