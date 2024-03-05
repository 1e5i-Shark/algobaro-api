package ei.algobaroapi.domain.room_member.exception;

import ei.algobaroapi.domain.room_member.exception.common.RoomMemberErrorCode;
import lombok.Getter;

@Getter
public class RoomMemberNotFoundException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private RoomMemberNotFoundException(RoomMemberErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static RoomMemberNotFoundException of(RoomMemberErrorCode errorCode) {
        return new RoomMemberNotFoundException(errorCode);
    }
}
