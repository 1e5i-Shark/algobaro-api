package ei.algobaroapi.domain.member.exception.common;

import ei.algobaroapi.domain.member.exception.MemberFoundException;
import ei.algobaroapi.domain.member.exception.MemberInputException;
import ei.algobaroapi.domain.member.exception.MemberPasswordException;
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
public class MemberExceptionHandler {

    @ExceptionHandler(MemberFoundException.class)
    public ResponseEntity<ErrorResponse> catchMemberFoundException(MemberFoundException e) {
        log.warn(e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }

    @ExceptionHandler(MemberInputException.class)
    public ResponseEntity<ErrorResponse> catchMemberInputException(MemberInputException e) {
        log.warn(e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }

    @ExceptionHandler(MemberPasswordException.class)
    public ResponseEntity<ErrorResponse> catchMemberPasswordException(MemberPasswordException e) {
        log.warn(e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }
}
