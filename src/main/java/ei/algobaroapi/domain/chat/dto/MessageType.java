package ei.algobaroapi.domain.chat.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageType {
    ENTER_ROOM("enter"),
    QUIT_ROOM("quit"),
    SEND_MESSAGE("message");

    @JsonValue
    private final String value;
}
