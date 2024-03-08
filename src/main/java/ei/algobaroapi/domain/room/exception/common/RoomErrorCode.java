package ei.algobaroapi.domain.room.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoomErrorCode {

    ROOM_NOT_FOUND("E03301", "방을 찾지 못했습니다.");

    private final String errorCode;
    private final String errorMessage;
}
