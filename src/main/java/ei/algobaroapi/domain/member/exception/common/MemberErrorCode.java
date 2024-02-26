package ei.algobaroapi.domain.member.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode {

    PASSWORD_NOT_MATCH("E01201", "비밀번호가 일치하지 않습니다."),
    ID_NOT_FOUND("E01301", "존재하지 않는 회원입니다."),
    EMAIL_NOT_FOUND("E01302", "가입되지 않은 이메일입니다.");

    private final String errorCode;
    private final String errorMessage;
}
