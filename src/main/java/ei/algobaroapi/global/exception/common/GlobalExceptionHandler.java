package ei.algobaroapi.global.exception.common;

import static ei.algobaroapi.global.exception.common.GlobalErrorCode.ACCESS_DENIED;
import static ei.algobaroapi.global.exception.common.GlobalErrorCode.INTERNAL_SERVER_ERROR;
import static ei.algobaroapi.global.exception.common.GlobalErrorCode.INVALID_INPUT_VALUE;

import ei.algobaroapi.global.exception.GlobalEntityException;
import ei.algobaroapi.global.response.message.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> catchAccessDeniedException(AccessDeniedException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.of(
                        ACCESS_DENIED.getErrorCode(),
                        ACCESS_DENIED.getErrorMessage())
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> catchMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                        INVALID_INPUT_VALUE.getErrorCode(),
                        INVALID_INPUT_VALUE.getErrorMessage())
                );
    }

    @ExceptionHandler(GlobalEntityException.class)
    public ResponseEntity<ErrorResponse> catchGlobalCustomException(GlobalEntityException e) {
        log.error(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                        e.getErrorCode(),
                        e.getErrorMessage())
                );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> catchMemberPasswordException(RuntimeException e) {
        log.error(e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(
                        INTERNAL_SERVER_ERROR.getErrorCode(),
                        INTERNAL_SERVER_ERROR.getErrorMessage())
                );
    }
}
