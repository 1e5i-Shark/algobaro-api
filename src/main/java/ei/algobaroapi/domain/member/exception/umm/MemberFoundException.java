package ei.algobaroapi.domain.member.exception.umm;

import ei.algobaroapi.domain.member.exception.MemberErrorCode;
import lombok.Getter;

@Getter
public class MemberFoundException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private MemberFoundException(MemberErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static MemberFoundException of(MemberErrorCode errorCode) {
        return new MemberFoundException(errorCode);
    }
}
