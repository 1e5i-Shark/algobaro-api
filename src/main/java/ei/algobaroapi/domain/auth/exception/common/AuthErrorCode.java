package ei.algobaroapi.domain.auth.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode {

    PASSWORD_NOT_MATCH("E00202", "비밀번호가 일치하지 않습니다."),
    EXISING_EMAIL("E00101", "이미 존재하는 이메일입니다."),
    PASSWORD_NOT_MATCH_PASSWORD_CONFIRMATION("E00001", "비밀번호와 비밀번호 확인이 일치하지 않습니다");

    private final String errorCode;
    private final String errorMessage;
}
