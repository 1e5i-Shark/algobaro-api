package ei.algobaroapi.domain.auth.exception.umm;

import ei.algobaroapi.domain.auth.exception.AuthErrorCode;
import lombok.Getter;

@Getter
public class MemberPasswordException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private MemberPasswordException(AuthErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static MemberPasswordException of(AuthErrorCode errorCode) {
        return new MemberPasswordException(errorCode);
    }
}
