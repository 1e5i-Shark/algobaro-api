package ei.algobaroapi.global.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorCode {

    ALREADY_DELETED_ENTITY("E00001", "이미 삭제된 엔티티 입니다."),
    ACCESS_DENIED("E00201", "접근 권한이 없습니다."),
    INVALID_INPUT_VALUE("E99001", null),
    INTERNAL_SERVER_ERROR("E99999", "서버 내부 오류가 발생했습니다.");

    private final String errorCode;
    private final String errorMessage;
}
