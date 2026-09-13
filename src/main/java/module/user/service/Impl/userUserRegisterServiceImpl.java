package module.user.service.Impl;

import common.exception.BusinessException;
import common.result.ResultCode;
import lombok.SneakyThrows;
import module.user.dto.userRegisterDTO;
import module.user.mapper.userMapper;
import module.user.service.aesService;
import module.user.service.userRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * register实现类
 */
@Service
public class userUserRegisterServiceImpl implements userRegisterService {

    @Autowired
    private userMapper userMapper;
    @Autowired
    private aesService aesService;

    @SneakyThrows
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(userRegisterDTO request) {

        //0.校验用户是否已存在（学号/工号为主键）
        if (userMapper.selectById(request.getID()) != null) {
            throw new BusinessException(ResultCode.USER_ALREADY_EXISTS);
        }

        //1.密码加密后入库
        request.setPasswordHash(aesService.encrypt(request.getPasswordHash()));
        userMapper.add(request);
    }
}
