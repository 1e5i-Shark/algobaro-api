package ei.algobaroapi.domain.auth.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode {

    PASSWORD_NOT_MATCH("E00202", "비밀번호가 일치하지 않습니다.");

    private final String errorCode;
    private final String errorMessage;
}
