package ei.algobaroapi.domain.member.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode {

    EMAIL_NOT_FOUND("E01300", "가입되지 않은 이메일입니다.");

    private final String errorCode;
    private final String errorMessage;
}
