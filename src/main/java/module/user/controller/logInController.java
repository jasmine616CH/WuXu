package module.user.controller;

import common.exception.BusinessException;
import common.result.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.user.dto.*;
import module.user.service.logInService;
import module.user.service.redisService;
import module.user.vo.logInVo;
import module.user.vo.tokenRefreshVo;
import org.springframework.web.bind.annotation.*;

/**
 * 登录服务实现类
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class logInController {

    private final logInService logInService;
    private final redisService redisService;

    /**
     * 用户登录接口
     *
     * @param loginDTO 登录请求参数
     * @return 成功返回相关参数，失败返回错误信息
     */
    @PostMapping("/login")
    public Result<logInVo> login(@RequestBody logInDTO loginDTO) {
        return Result.success(logInService.logIn(loginDTO));
    }

    /**
     * 刷新token接口
     */
    @PostMapping("/refresh-token")
    public Result<tokenRefreshVo> refreshToken(
            @Valid @RequestBody tokenRefreshDTO request) {

        try {
            // 1. 验证refreshToken
            if (!redisService.validateRefreshToken(request.getRefreshToken())) {
                return Result.error(401, "刷新令牌无效或已过期，请重新登录");
            }

            // 2. 刷新token
            tokenDTO tokenDTO = redisService.refreshAccessToken(
                    request.getRefreshToken());

            // 3. 构建响应
            tokenRefreshVo vo = tokenRefreshVo.builder()
                    .accessToken(tokenDTO.getAccessToken())
                    .refreshToken(tokenDTO.getRefreshToken())
                    .expiresIn(tokenDTO.getExpiresIn())
                    .refreshExpiresIn(tokenDTO.getRefreshExpiresIn())
                    .tokenType(tokenDTO.getTokenType())
                    .build();

            return Result.success(vo);

        } catch (BusinessException e) {
            // 处理刷新异常（包括重放攻击检测）
            log.warn("Token刷新失败：{}", e.getMessage());
            return Result.error(40005, "Token刷新失败");
        } catch (Exception e) {
            log.error("Token刷新异常", e);
            return Result.error(40006, "Token刷新异常");
        }
    }

    /**
     * 用户登出
     */
    @PostMapping("/logout")
    public Result<Void> logout(@Valid @RequestBody tokenLogoutDTO request) {
        try {
            redisService.logout(LogOutDTO.builder()
                    .accessToken(request.getAccessToken())
                    .build());
            return Result.success();
        } catch (Exception e) {
            log.error("登出异常", e);
            return Result.error(40404, "登出失败");
        }
    }



}
