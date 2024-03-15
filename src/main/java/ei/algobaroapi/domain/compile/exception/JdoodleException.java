package ei.algobaroapi.domain.compile.exception;

import ei.algobaroapi.domain.compile.exception.common.CompileErrorCode;
import lombok.Getter;

@Getter
public class JdoodleException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private JdoodleException(CompileErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static JdoodleException of(CompileErrorCode errorCode) {
        return new JdoodleException(errorCode);
    }
}
