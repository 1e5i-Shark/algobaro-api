package ei.algobaroapi.domain.solve.exception;

import ei.algobaroapi.domain.solve.exception.common.SolveErrorCode;
import lombok.Getter;

@Getter
public class SolveFoundException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private SolveFoundException(SolveErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static SolveFoundException of(SolveErrorCode errorCode) {
        return new SolveFoundException(errorCode);
    }
}
