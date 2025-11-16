package ms.thakur.lodhi.utility.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepositoryResult<T> {

    private T result;
    private boolean success;
    private String message;

    /**
     * for paginated Results
     */

    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalItems;


    RepositoryResult(T result, boolean success, String message) {
        this.result = result;
        this.success = success;
        this.message = message;
    }

    public static <T> RepositoryResult<T> createSuccessResult(T result, String message) {
        return new RepositoryResult<T>(result,true,message);
    }

    public static <T> RepositoryResult<T> createFailureResult(String message) {
        return new RepositoryResult<T>(null,false,message);
    }

}
