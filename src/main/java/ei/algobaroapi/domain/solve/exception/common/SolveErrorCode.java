package ei.algobaroapi.domain.solve.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SolveErrorCode {

    SOLVE_HISTORY_NOT_FOUND("E02301", "존재하지 않는 풀이 내역입니다."),
    SOLVE_HISTORY_ACCESS_DENIED("E02201", "해당 풀이 내역에 접근할 수 없습니다.");

    private final String errorCode;
    private final String errorMessage;
}
