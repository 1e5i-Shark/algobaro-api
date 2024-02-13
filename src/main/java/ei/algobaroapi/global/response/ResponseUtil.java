package ei.algobaroapi.global.response;

import ei.algobaroapi.global.response.message.BasicResponse;
import ei.algobaroapi.global.response.message.ErrorResponse;

public class ResponseUtil {

    public static <T> BasicResponse<T> success(T response) {
        return new BasicResponse<>(true, response, null);
    }

    public static BasicResponse<?> error(ErrorResponse e) {
        return new BasicResponse<>(false, null, e);
    }
}