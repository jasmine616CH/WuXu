package module.user.service.Impl;

import module.user.service.aesService;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES实现类
 */
@Service
public class aesServiceImpl implements aesService {

    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 128;
    private static final int GCM_IV_LENGTH = 12;
    // 必须是 16 / 24 / 32 字节，当前为 32 字节（AES-256）
    private static final String SECRET_KEY = "0123456789abcdef0123456789abcdef";

    /**
     * 加密
     * @param password 密码
     * @return 加密密码
     */
    @Override
    public String encrypt(String password) throws Exception{
        //1.随机生成IV（初始化向量）
        byte[] iv = new byte[GCM_IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

        //2.准备密钥
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes() , "AES");

        //3.执行加密
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec , spec);
        byte[] encryptedData = cipher.doFinal(password.getBytes());

        //4.将IV和密钥合，并转换成bean64存储
        byte[] combined = new byte[iv.length + encryptedData.length];
        System.arraycopy(iv , 0 , combined ,0 , iv.length);
        System.arraycopy(encryptedData , 0 , combined , iv.length , encryptedData.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    /**
     * 解密
     * @param encryptedPassword 加密密码
     * @return 密码
     */
    @Override
    public String decrypt(String encryptedPassword) throws Exception{

        //1.Base64解密
        byte[] combined = Base64.getDecoder().decode(encryptedPassword);

        //2.分离IV和密文
        byte[] iv = new byte[GCM_IV_LENGTH];
        byte[] cipherText = new byte[combined.length - GCM_IV_LENGTH];
        System.arraycopy(combined , 0 , iv , 0 , GCM_IV_LENGTH);
        System.arraycopy(combined , GCM_IV_LENGTH , cipherText , 0 , cipherText.length);

        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH , iv);
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes() , "AES");

        //3.执行解密
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec , spec);
        byte[] decryptedPassword = cipher.doFinal(cipherText);

        return new String(decryptedPassword);
    }
}
