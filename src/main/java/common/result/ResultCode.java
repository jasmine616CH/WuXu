package common.result;

import common.exception.BusinessException;
import lombok.Getter;

@Getter
public enum ResultCode {

    /**
     * 状态码
     */
    SUCCESS(1,"success"),
    ERROR(0 , "error"),

    /**
     * 用户模块
     */
    USER_NOT_FOUND(400101 , "用户不存在"),
    PASSWORD_ERROR(400102 , "密码错误"),
    PHONE_FORMAT_ERROR(400103 , "手机号格式不对"),
    USER_NOT_LOGIN(400104 , "用户未登录"),

    /**
     * token模块
     */
    TOKEN_EXPIRED(400201 , "Token已过期"),
    TOKEN_SIGNATURE_ERROR(400202 , "Token签名异常"),
    TOKEN_MALFORMED(400203 , "Token格式不对"),
    TOKEN_INVALID(400204 , "Token无效"),

    /**
     * 物品模块
     */
    GOODS_NOT_FOUND(400301 , "物品不存在或已删除"),
    GOODS_IN_MAINTAIN(400302 , "物品维修中"),

    /**
     * excel模块
     */
    EXCEL_IS_NULL(400401 , "excel表不能为空"),
    EXCEL_LARGE_SIZE(400402 , "excel表内存超过50MB"),
    EXCEL_FORMAT_INCORRECT(400403 , "请上传正确的 Excel 文件（后缀为 .xlsx 或 .xls）"),

    ;

    private final int code;
    private final String massage;

    private ResultCode(int code , String massage){
        this.code = code;
        this.massage = massage;
    }

}
