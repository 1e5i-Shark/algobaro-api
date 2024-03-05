package ei.algobaroapi.domain.auth.exception.common;

import ei.algobaroapi.domain.auth.exception.AuthEmailExistenceException;
import ei.algobaroapi.domain.auth.exception.AuthInputException;
import ei.algobaroapi.domain.auth.exception.AuthNicknameExistenceException;
import ei.algobaroapi.domain.auth.exception.AuthPasswordException;
import ei.algobaroapi.global.response.message.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(AuthPasswordException.class)
    public ResponseEntity<ErrorResponse> catchMemberPasswordException(AuthPasswordException e) {
        log.warn(e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }

    @ExceptionHandler(AuthEmailExistenceException.class)
    public ResponseEntity<ErrorResponse> catchNonExistentEmail(AuthEmailExistenceException e) {
        log.warn(e.getErrorCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }

    @ExceptionHandler(AuthInputException.class)
    public ResponseEntity<ErrorResponse> catchAuthInputException(AuthInputException e) {
        log.warn(e.getErrorCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }

    @ExceptionHandler(AuthNicknameExistenceException.class)
    public ResponseEntity<ErrorResponse> catchNonExistentNickname(
            AuthNicknameExistenceException e) {
        log.warn(e.getErrorCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }
}
