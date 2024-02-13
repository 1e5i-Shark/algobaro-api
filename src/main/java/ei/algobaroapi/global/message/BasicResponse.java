package ei.algobaroapi.global.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicResponse<T> {

    private boolean success;
    private T response;
    private ErrorResponse error;
}
