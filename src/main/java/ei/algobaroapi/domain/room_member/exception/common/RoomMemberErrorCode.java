package ei.algobaroapi.domain.room_member.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoomMemberErrorCode {

    ROOM_MEMBER_ERROR_CODE("E05301", "해당 방에 멤버를 찾지 못했습니다."),
    ROOM_MEMBER_IS_NOT_HOST("E05302", "방장 권한을 위임할 수 있는 권한을 가지고 있지 않습니다."),
    ROOM_MEMBER_IS_NOT_PARTICIPANT("E05303", "방장 권한을 위임 받을 수 있는 참여자가 아닙니다."),
    ROOM_MEMBER_IS_NOT_READY("E05101", "준비 상태가 아닌 멤버가 있습니다.");

    private final String errorCode;
    private final String errorMessage;
}
