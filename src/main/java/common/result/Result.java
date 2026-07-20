package common.result;

import common.exception.BusinessException;
import lombok.Data;

@Data
public class Result {

    private Integer code;

    private String massage;

    private Object date;

    public static Result success(Object date){
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setDate(date);
        result.setMassage("success");
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code , String massage){
        Result result = new Result();
        result.setCode(code);
        result.setDate(null);
        result.setMassage(massage);
        return result;
    }

    public static Result error(BusinessException b){
        Result result = new Result();
        result.setCode(b.getResultCode().getCode());
        result.setMassage(b.getResultCode().getMassage());
        result.setDate(null);
        return result;
    }

}
