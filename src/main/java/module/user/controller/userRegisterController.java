package module.user.controller;

import common.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.user.dto.userRegisterDTO;
import module.user.service.userRegisterService;
import module.user.vo.userRegisterVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册控制器
 */

@Slf4j
@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class userRegisterController {

    private final userRegisterService registerService;

    /**
     * 用户注册接口
     *
     * @param userRegisterVo 注册请求参数
     * @return 成功返回统一结果，错误返回错误信息
     */
    @PostMapping
    public Result register(@RequestBody userRegisterVo userRegisterVo){
        
        //vo转dto
        userRegisterDTO userRegisterDTO = new userRegisterDTO();
        BeanUtils.copyProperties(userRegisterVo , userRegisterDTO);
        
        registerService.register(userRegisterDTO);
        return Result.success();
    }

}
