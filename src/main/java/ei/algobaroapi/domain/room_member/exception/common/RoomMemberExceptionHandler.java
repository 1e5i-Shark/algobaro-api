package ei.algobaroapi.domain.room_member.exception.common;

import ei.algobaroapi.domain.room_member.exception.HostValidationException;
import ei.algobaroapi.domain.room_member.exception.OrganizerValidationException;
import ei.algobaroapi.domain.room_member.exception.RoomMemberNotEnterException;
import ei.algobaroapi.domain.room_member.exception.RoomMemberNotFoundException;
import ei.algobaroapi.domain.room_member.exception.RoomMemberNotReadyException;
import ei.algobaroapi.global.response.message.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RoomMemberExceptionHandler {

    @ExceptionHandler(HostValidationException.class)
    public ResponseEntity<ErrorResponse> handleHostValidationException(HostValidationException e) {
        log.warn(e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }

    @ExceptionHandler(OrganizerValidationException.class)
    public ResponseEntity<ErrorResponse> handleOrganizerValidationException(
            OrganizerValidationException e) {
        log.warn(e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }      
      
    @ExceptionHandler(RoomMemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> catchRoomMemberNotFountException(RoomMemberNotFoundException e) {
        log.warn(e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }

    @ExceptionHandler(RoomMemberNotReadyException.class)
    public ResponseEntity<ErrorResponse> catchRoomMemberNotReadyException(RoomMemberNotReadyException e) {
        log.warn(e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }

    @ExceptionHandler(RoomMemberNotEnterException.class)
    public ResponseEntity<ErrorResponse> catchRoomMemberNotEnterException(RoomMemberNotEnterException e) {
        log.warn(e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }
}
