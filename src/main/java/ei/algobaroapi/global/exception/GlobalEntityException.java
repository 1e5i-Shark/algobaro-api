package ei.algobaroapi.global.exception;

import ei.algobaroapi.global.exception.common.GlobalErrorCode;
import lombok.Getter;

@Getter
public class GlobalEntityException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private GlobalEntityException(GlobalErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static GlobalEntityException of(GlobalErrorCode errorCode) {
        return new GlobalEntityException(errorCode);
    }
}
