package ei.algobaroapi.domain.room.exception;

import ei.algobaroapi.domain.room.exception.common.RoomErrorCode;
import lombok.Getter;

@Getter
public class RoomNotReadyException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private RoomNotReadyException(RoomErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static RoomNotReadyException of(RoomErrorCode errorCode) {
        return new RoomNotReadyException(errorCode);
    }
}
