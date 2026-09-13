package module.user.controller;

import common.result.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import module.user.dto.userInfoDTO;
import module.user.service.userInfoService;
import module.user.vo.userInfoVo;
import module.user.vo.userProfileVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息操作控制器
 */
@RequestMapping("/api/user/info")
@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class userInfoController {

    private final userInfoService userInfoService;

    /**
     * 获取当前登录用户基本信息接口
     * @return 用户基本信息
     */
    @PostMapping("/base")
    public Result<userInfoVo> getUserInfo(){
        return Result.success(userInfoService.getUserInfo());
    }

    /**
     * 获取当前登录用户详细信息
     * @return 用户详细信息
     */
    @PostMapping("/profile")
    public Result<userProfileVo> getUserProfile(){
        return Result.success(userInfoService.getUserProfile());
    }

    /**
     * 修改用户密码
     * @param userInfoDTO 用户信息
     * @return 成功返回相关信息 失败返回错误信息
     */
    @PostMapping("/password")
    public Result<String> updatePassword(@Valid@RequestBody userInfoDTO userInfoDTO){
        userInfoService.updatePassword(userInfoDTO);
        return Result.success();
    }

    /**
     * 修改用户手机号
     * @param userInfoDTO 用户信息
     * @return 成功返回相关信息 失败返回失败信息
     */
    @PostMapping("/update")
    public Result<String> updateUserInfo(@Valid@RequestBody userInfoDTO userInfoDTO){
        userInfoService.updateUserInfo(userInfoDTO);
        return Result.success();
    }

}
