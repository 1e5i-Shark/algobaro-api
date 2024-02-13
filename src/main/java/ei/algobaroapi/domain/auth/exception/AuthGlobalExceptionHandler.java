package ei.algobaroapi.domain.auth.exception;

import ei.algobaroapi.domain.auth.exception.umm.AuthPasswordException;
import ei.algobaroapi.global.response.message.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AuthGlobalExceptionHandler {

    @ExceptionHandler(AuthPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse catchMemberPasswordException(AuthPasswordException e) {
        log.warn(e.getErrorMessage());
        return ErrorResponse.of(e.getErrorCode(), e.getErrorMessage());
    }
}
