package ei.algobaroapi.domain.problem.exception;

import ei.algobaroapi.domain.problem.exception.common.ProblemErrorCode;
import lombok.Getter;

@Getter
public class CrawlingAccessException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private CrawlingAccessException(ProblemErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static CrawlingAccessException of(ProblemErrorCode errorCode) {
        return new CrawlingAccessException(errorCode);
    }
}
