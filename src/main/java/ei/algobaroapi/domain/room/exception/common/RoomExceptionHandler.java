package ei.algobaroapi.domain.room.exception.common;

import ei.algobaroapi.domain.room.exception.RoomNotFoundException;
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
public class RoomExceptionHandler {

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<ErrorResponse> catchRoomNotFountException(RoomNotFoundException e) {
        log.warn(e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }
}
