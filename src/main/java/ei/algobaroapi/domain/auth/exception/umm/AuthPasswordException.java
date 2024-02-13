package ei.algobaroapi.domain.auth.exception.umm;

import ei.algobaroapi.domain.auth.exception.AuthErrorCode;
import lombok.Getter;

@Getter
public class AuthPasswordException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private AuthPasswordException(AuthErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static AuthPasswordException of(AuthErrorCode errorCode) {
        return new AuthPasswordException(errorCode);
    }
}
