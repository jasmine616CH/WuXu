package module.user.dto;

import lombok.Data;

@Data
public class logInDTO {

    /**
     * ID
     */
    private String ID;

    /**
     * 密码 - 加密
     */
    private String passwordHash;

}
