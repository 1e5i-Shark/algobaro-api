package ei.algobaroapi.domain.problem.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProblemErrorCode {

    CRAWLING_NOT_ACCESS("E040301", "크롤링에 실패하였습니다.");

    private final String errorCode;
    private final String errorMessage;
}
