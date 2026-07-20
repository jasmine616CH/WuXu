package module.user.service;

/**
 * 密码存储加密业务接口
 * 实现用户密码加密存储和解密功能
 */
public interface aesService {

    /**
     * AES密码加密
     */
    String encrypt(String password) throws Exception;

    /**
     * AES密码解密
     */
    String decrypt(String encryptedPassword) throws Exception;

}
