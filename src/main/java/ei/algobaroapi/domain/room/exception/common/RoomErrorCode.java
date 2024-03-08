package ei.algobaroapi.domain.room.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoomErrorCode {

    ROOM_NOT_FOUND("E03301", "방을 찾지 못했습니다."),
    ROOM_NOT_READY("E03101", "방 인원 중 준비 상태가 아닌 멤버가 있습니다.");

    private final String errorCode;
    private final String errorMessage;
}
