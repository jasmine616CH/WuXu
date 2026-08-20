package module.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogOutDTO {

    /**
     * 主键ID
     */
    private String userID;

    /**
     *成功的token
     */
    private String accessToken;

    /**
     * 刷新token
     */
    private String refreshToken;

}
