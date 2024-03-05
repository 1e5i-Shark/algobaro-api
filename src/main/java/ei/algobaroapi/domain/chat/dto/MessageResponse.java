package ei.algobaroapi.domain.chat.dto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageResponse {

    private final String userId;
    private final MessageType type;
    private final String value;
    private final String timestamp = LocalDateTime.now().toString();

    public static MessageResponse enterRoom(String userId) {
        return new MessageResponse(
                userId,
                MessageType.ENTER_ROOM,
                null
        );
    }

    public static MessageResponse quitRoom(String userId) {
        return new MessageResponse(
                userId,
                MessageType.QUIT_ROOM,
                null
        );
    }

    public static MessageResponse sendMessage(
            String userId,
            String value
    ) {
        return new MessageResponse(
                userId,
                MessageType.SEND_MESSAGE,
                value
        );
    }
}
