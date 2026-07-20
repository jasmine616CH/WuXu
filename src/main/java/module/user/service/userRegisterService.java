package module.user.service;

import module.user.dto.userRegisterDTO;

/**
 * 注册业务服务窗口
 */
public interface userRegisterService {

    /**
     * 用户注册
     *
     * @param request 学生教职工注册参数
     */
    void register(userRegisterDTO request);

}
