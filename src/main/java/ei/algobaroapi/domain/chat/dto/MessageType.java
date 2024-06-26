package ei.algobaroapi.domain.chat.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageType {
    ENTER_ROOM("enter"),
    QUIT_ROOM("quit"),
    SEND_MESSAGE("message"),
    READY_ROOM("ready"),
    UNREADY_ROOM("unready"),
    CHANGE_HOST("change-host"),
    START_CODING("start-coding"),
    END_CODING("end-coding"),
    VOLUME("volume");

    @JsonValue
    private final String value;
}
