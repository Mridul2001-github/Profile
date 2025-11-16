package ms.thakur.lodhi.common.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebApiResult<T> {

    private T result;

    /**
     * for paginated Response
     */
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private String totalItems;

    /**
     * Type of Result
     */
    private String message;
    private String resultType;

    WebApiResult(T result, String message, String resultType) {
        this.result = result;
        this.message = message;
        this.resultType = resultType;
    }

    public static <T> WebApiResult<T> createSuccessResult(
            T result, String message
    ){
        return new WebApiResult<T>(result, message,"SUCCESS");
    }

    public static <T> WebApiResult<T> createFailureResult(
            String message
    ) {
        return new WebApiResult<T>(null, message, "FAILURE");
    }

}
