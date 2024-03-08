package ei.algobaroapi.domain.room_member.exception;

import ei.algobaroapi.domain.room_member.exception.common.RoomMemberErrorCode;
import lombok.Getter;

@Getter
public class RoomMemberNotEnterException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private RoomMemberNotEnterException(RoomMemberErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static RoomMemberNotEnterException of(RoomMemberErrorCode errorCode) {
        return new RoomMemberNotEnterException(errorCode);
    }
}
