package ei.algobaroapi.domain.solve.exception.common;

import ei.algobaroapi.domain.solve.exception.SolveAccessException;
import ei.algobaroapi.domain.solve.exception.SolveFoundException;
import ei.algobaroapi.global.response.message.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class SolveExceptionHandler {

    @ExceptionHandler(SolveFoundException.class)
    public ResponseEntity<ErrorResponse> catchSolveFoundException(SolveFoundException e) {
        log.warn(e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }

    @ExceptionHandler(SolveAccessException.class)
    public ResponseEntity<ErrorResponse> catchSolveAccessException(SolveAccessException e) {
        log.warn(e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }
}
