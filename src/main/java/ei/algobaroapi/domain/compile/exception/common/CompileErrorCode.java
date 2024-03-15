package ei.algobaroapi.domain.compile.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompileErrorCode {

    JDOODLE_API_EXCEPTION("E07401", "Jdoodle API 호출 중 오류가 발생했습니다.");

    private final String errorCode;
    private final String errorMessage;
}
