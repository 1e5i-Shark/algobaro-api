package ei.algobaroapi.domain.room_member.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoomMemberErrorCode {

    ROOM_MEMBER_ERROR_CODE("E05301", "해당 방에 멤버를 찾지 못했습니다.");

    private final String errorCode;
    private final String errorMessage;
}
