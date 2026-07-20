package common.enums;

import lombok.Getter;

/**
 * 用户身份
 */
@Getter
public enum userType {

    STATUS(1, "ROLE_STUDENT" , "学生"),
    ADMINISTRATOR(0, "ROLE_TEACHER" ,"管理员")
    ;

    private final int code;
    private final String authority;
    private final String desc;

    userType(int code , String desc , String authority){
        this.code = code ;
        this.authority = authority;
        this.desc = desc;
    }

    /**
     *根据枚举名称获取实例
     * @return 匹配的枚举对象
     */
    public static userType getByCode(Integer code){
        if (code == null)
            return null;
        for (userType e : values()){
            if (e.getCode() == code){
                return e;
            }
        }
        return null;
    }

    /**
     * 根据枚举名称获取代码
     * @return 代码名称
     */
    public static String getNameByCode(Integer code){
        if (code == null )
            return null;
        for (userType e : values()){
            if (e.getCode() == code){
                return e.getDesc();
            }
        }
        return null;
    }

}
