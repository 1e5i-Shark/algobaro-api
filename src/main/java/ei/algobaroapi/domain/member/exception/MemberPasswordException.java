package ei.algobaroapi.domain.member.exception;

import ei.algobaroapi.domain.member.exception.common.MemberErrorCode;
import lombok.Getter;

@Getter
public class MemberPasswordException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private MemberPasswordException(MemberErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static MemberPasswordException of(MemberErrorCode errorCode) {
        return new MemberPasswordException(errorCode);
    }
}
