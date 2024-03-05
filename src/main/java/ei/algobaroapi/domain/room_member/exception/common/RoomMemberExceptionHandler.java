package ei.algobaroapi.domain.room_member.exception.common;

import ei.algobaroapi.domain.room_member.exception.RoomMemberNotFoundException;
import ei.algobaroapi.global.response.message.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RoomMemberExceptionHandler {

    @ExceptionHandler(RoomMemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> catchRoomMemberNotFountException(RoomMemberNotFoundException e) {
        log.warn(e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }
}
