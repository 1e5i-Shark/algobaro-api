package ei.algobaroapi.domain.solve.exception;

import ei.algobaroapi.domain.solve.exception.common.SolveErrorCode;
import lombok.Getter;

@Getter
public class SolveAccessException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private SolveAccessException(SolveErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static SolveAccessException of(SolveErrorCode errorCode) {
        return new SolveAccessException(errorCode);
    }
}
