package ei.algobaroapi.domain.room.exception;

import ei.algobaroapi.domain.room.exception.common.RoomErrorCode;
import lombok.Getter;

@Getter
public class RoomNotFoundException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private RoomNotFoundException(RoomErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static RoomNotFoundException of(RoomErrorCode errorCode) {
        return new RoomNotFoundException(errorCode);
    }
}
