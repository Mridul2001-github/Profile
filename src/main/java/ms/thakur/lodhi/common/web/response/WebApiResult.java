package ms.thakur.lodhi.common.web.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebApiResult<T> {

    private T result;
    private boolean success;
    private String message;
    private String resultType;

    WebApiResult(T result, boolean success, String message, String resultType) {
        this.result = result;
        this.success = success;
        this.message = message;
        this.resultType = resultType;
    }

    public static <T> WebApiResult<T> createSuccessResult(
            T result, String message
    ){
        return new WebApiResult<T>(result,true,message,"SUCCESS");
    }

    public static <T> WebApiResult<T> createFailureResult(
            String message
    ){
        return new WebApiResult<T>(null,false,message,"FAILURE");
    }

}
