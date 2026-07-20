package module.user.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import common.exception.BusinessException;
import common.result.ResultCode;
import common.until.SecurityUtils;
import lombok.RequiredArgsConstructor;
import module.user.dto.userInfoDTO;
import module.user.dto.userRegisterDTO;
import module.user.entity.User;
import module.user.mapper.userMapper;
import module.user.service.aesService;
import module.user.service.userInfoService;
import module.user.service.userService;
import module.user.vo.userInfoVo;
import module.user.vo.userProfileVo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class userInfoServiceImpl implements userInfoService {

    private final userMapper userMapper;
    private final userService userService;
    private final aesService aesService;

    /**
     * 获取当前用户基本信息
     */
    @Override
    public userInfoVo getUserInfo() {

        //1.获取当前登录用户ID
        User user = userMapper.selectById(SecurityUtils.getCurrentID());

        //2.转换为VO
        userInfoVo userInfoVo = new userInfoVo();
        BeanUtil.copyProperties(user , userInfoVo);

        return userInfoVo;
    }

    /**
     * 获取用户扩展信息
     * @return 用户扩展信息
     */
    @Override
    public userProfileVo getUserProfile() {
        return userService.getUserProfile(SecurityUtils.getCurrentID());
    }

    /**
     *修改密码
     * @param userInfoDTO 用户信息请求体
     */
    @Override
    public void updatePassword(userInfoDTO userInfoDTO){

        //1.检验用户是否存在
        User user = userMapper.selectById(SecurityUtils.getCurrentID());
        if (user == null){
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }

        //2.修改密码
        try {
            user.setPassword(aesService.decrypt(userInfoDTO.getPasswordHash()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //3.保存信息
        userMapper.updateById(user);

    }

    /**
     * 修改手机号
     * @param userInfoDTO 用户信息参数
     */
    @Override
    public void updateUserInfo(userInfoDTO userInfoDTO) {

        //1.校验用户是否存在
        User user = userMapper.selectById(SecurityUtils.getCurrentID());
        if (user == null){
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }

        //2.修改手机号
        user.setPhone(userInfoDTO.getPhone());

        //3.保存信息
        userMapper.updateById(user);

    }

}
