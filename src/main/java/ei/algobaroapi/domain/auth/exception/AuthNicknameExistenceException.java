package ei.algobaroapi.domain.auth.exception;

import ei.algobaroapi.domain.auth.exception.common.AuthErrorCode;
import lombok.Getter;

@Getter
public class AuthNicknameExistenceException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private AuthNicknameExistenceException(AuthErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static AuthNicknameExistenceException of(AuthErrorCode errorCode) {
        return new AuthNicknameExistenceException(errorCode);
    }
}
