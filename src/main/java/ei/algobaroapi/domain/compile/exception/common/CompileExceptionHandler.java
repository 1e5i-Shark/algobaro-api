package ei.algobaroapi.domain.compile.exception.common;

import ei.algobaroapi.domain.compile.exception.JdoodleException;
import ei.algobaroapi.global.response.message.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CompileExceptionHandler {

    @ExceptionHandler(JdoodleException.class)
    public ResponseEntity<ErrorResponse> catchJdoodleException(JdoodleException e) {
        log.error(e.getErrorCode());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                        e.getErrorCode(),
                        e.getErrorMessage())
                );
    }
}
