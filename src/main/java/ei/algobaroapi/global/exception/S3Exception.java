package ei.algobaroapi.global.exception;

import ei.algobaroapi.global.exception.common.GlobalErrorCode;
import lombok.Getter;

@Getter
public class S3Exception extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private S3Exception(GlobalErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static S3Exception of(GlobalErrorCode errorCode) {
        return new S3Exception(errorCode);
    }
}

