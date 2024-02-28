package ei.algobaroapi.domain.auth.exception;

import ei.algobaroapi.domain.auth.exception.common.AuthErrorCode;
import lombok.Getter;

@Getter
public class AuthInputException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private AuthInputException(AuthErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static AuthInputException of(AuthErrorCode errorCode) {
        return new AuthInputException(errorCode);
    }
}
