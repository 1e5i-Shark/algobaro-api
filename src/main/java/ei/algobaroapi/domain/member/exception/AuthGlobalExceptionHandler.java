package ei.algobaroapi.domain.member.exception;

import ei.algobaroapi.domain.member.exception.umm.MemberFoundException;
import ei.algobaroapi.domain.member.exception.umm.MemberPasswordException;
import ei.algobaroapi.global.message.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AuthGlobalExceptionHandler {

    @ExceptionHandler(MemberFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse catchMemberFoundException(MemberFoundException e) {
        log.warn(e.getErrorMessage());
        return ErrorResponse.of(e.getErrorCode(), e.getErrorMessage());
    }

    @ExceptionHandler(MemberPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse catchMemberPasswordException(MemberPasswordException e) {
        log.warn(e.getErrorMessage());
        return ErrorResponse.of(e.getErrorCode(), e.getErrorMessage());
    }
}
