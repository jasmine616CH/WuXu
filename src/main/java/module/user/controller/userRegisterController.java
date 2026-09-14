package module.user.controller;

import common.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.user.dto.userRegisterDTO;
import module.user.service.userRegisterService;
import org.springframework.web.bind.annotation.*;

/**
 * 用户注册控制器
 */

@Slf4j
@RestController
@RequestMapping("/api/user/register")
@RequiredArgsConstructor
public class userRegisterController {

    private final userRegisterService registerService;

    /**
     * 用户注册接口
     *
     * @param dto 注册请求参数
     * @return 成功返回统一结果，错误返回错误信息
     */
    @PostMapping
    public Result<String> register(@RequestBody userRegisterDTO dto){
        registerService.register(dto);
        return Result.success();
    }

}
