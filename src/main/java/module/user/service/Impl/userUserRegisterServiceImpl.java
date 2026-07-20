package module.user.service.Impl;

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
        request.setPassword_hash(aesService.encrypt(request.getPassword_hash()));
        userMapper.add(request);
    }
}
