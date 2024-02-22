package ei.algobaroapi.domain.auth.exception;

import ei.algobaroapi.domain.auth.exception.common.AuthErrorCode;
import lombok.Getter;

@Getter
public class AuthPasswordConfirmationException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private AuthPasswordConfirmationException(AuthErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static AuthPasswordConfirmationException of(AuthErrorCode errorCode) {
        return new AuthPasswordConfirmationException(errorCode);
    }
}
