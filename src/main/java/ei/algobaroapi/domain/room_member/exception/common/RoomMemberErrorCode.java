package ei.algobaroapi.domain.room_member.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoomMemberErrorCode {

    ROOM_MEMBER_IS_NOT_HOST("E05302", "방장 권한을 위임할 수 있는 권한을 가지고 있지 않습니다."),
    ROOM_MEMBER_IS_NOT_PARTICIPANT("E05303", "방장 권한을 위임 받을 수 있는 참여자가 아닙니다.");

    private final String errorCode;
    private final String errorMessage;
}
