package common.result;

import lombok.Data;

@Data
public class Effect<T> {

    private Integer code;

    private String massage;

    private T date;

    public static <T> Effect<T> success(T data){

        Effect<T> effect = new Effect<>();
        effect.setCode(ResultCode.SUCCESS.getCode());
        effect.setDate(data);
        effect.setMassage("success");
        return effect;

    }

    public static <T> Effect<T> error(String msg){

        Effect<T> effect = new Effect<>();
        effect.setCode(404);
        effect.setMassage(msg);
        return effect;

    }

}
