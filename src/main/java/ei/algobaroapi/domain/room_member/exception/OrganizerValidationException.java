package ei.algobaroapi.domain.room_member.exception;

import ei.algobaroapi.domain.room_member.exception.common.RoomMemberErrorCode;
import lombok.Getter;

@Getter
public class OrganizerValidationException extends RuntimeException{

    private final String errorCode;
    private final String errorMessage;

    private OrganizerValidationException(RoomMemberErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static OrganizerValidationException of(RoomMemberErrorCode errorCode) {
        return new OrganizerValidationException(errorCode);
    }
}
