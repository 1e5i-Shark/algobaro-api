package ei.algobaroapi.domain.room_member.exception;

import ei.algobaroapi.domain.room.exception.common.RoomErrorCode;
import lombok.Getter;

@Getter
public class RoomMemberNotReadyException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private RoomMemberNotReadyException(RoomErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static RoomMemberNotReadyException of(RoomErrorCode errorCode) {
        return new RoomMemberNotReadyException(errorCode);
    }
}
