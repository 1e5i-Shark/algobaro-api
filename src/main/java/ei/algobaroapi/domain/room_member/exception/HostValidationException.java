package ei.algobaroapi.domain.room_member.exception;

import ei.algobaroapi.domain.room_member.exception.common.RoomMemberErrorCode;
import lombok.Getter;

@Getter
public class HostValidationException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private HostValidationException(RoomMemberErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static HostValidationException of(RoomMemberErrorCode errorCode) {
        return new HostValidationException(errorCode);
    }
}
