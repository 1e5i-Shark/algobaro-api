package ei.algobaroapi.domain.auth.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode {

    EMAIL_NOT_FOUND("E0300", "가입되지 않은 이메일입니다."),
    PASSWORD_NOT_MATCH("E0201", "비밀번호가 일치하지 않습니다.");

    private final String errorCode;
    private final String errorMessage;
}
