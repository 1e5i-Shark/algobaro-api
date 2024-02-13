package ei.algobaroapi.domain.member.exception.common;

import ei.algobaroapi.domain.member.exception.MemberFoundException;
import ei.algobaroapi.global.response.message.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MemberExceptionHandler {

    @ExceptionHandler(MemberFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse catchMemberFoundException(MemberFoundException e) {
        log.warn(e.getErrorMessage());
        return ErrorResponse.of(e.getErrorCode(), e.getErrorMessage());
    }
}
