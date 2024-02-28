package ei.algobaroapi.domain.member.exception;

import ei.algobaroapi.domain.member.exception.common.MemberErrorCode;
import lombok.Getter;

@Getter
public class MemberInputException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private MemberInputException(MemberErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static MemberInputException of(MemberErrorCode errorCode) {
        return new MemberInputException(errorCode);
    }
}
