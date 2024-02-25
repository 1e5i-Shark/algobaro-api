package ei.algobaroapi.domain.member.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode {

    ID_NOT_FOUND("E01301", "존재하지 않는 회원입니다."),
    EMAIL_NOT_FOUND("E01302", "가입되지 않은 이메일입니다.");

    private final String errorCode;
    private final String errorMessage;
}
