package ei.algobaroapi.domain.chat.dto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageResponse {

    private final Long memberId;
    private final MessageType type;
    private final String value;
    private final String timestamp = LocalDateTime.now().toString();

    public static MessageResponse enterRoom(Long memberId) {
        return new MessageResponse(
                memberId,
                MessageType.ENTER_ROOM,
                null
        );
    }

    public static MessageResponse quitRoom(Long memberId) {
        return new MessageResponse(
                memberId,
                MessageType.QUIT_ROOM,
                null
        );
    }

    public static MessageResponse sendMessage(
            Long memberId,
            String value
    ) {
        return new MessageResponse(
                memberId,
                MessageType.SEND_MESSAGE,
                value
        );
    }

    public static MessageResponse readyRoom(Long memberId) {
        return new MessageResponse(
                memberId,
                MessageType.READY_ROOM,
                null
        );
    }

    public static MessageResponse unreadyRoom(Long memberId) {
        return new MessageResponse(
                memberId,
                MessageType.UNREADY_ROOM,
                null
        );
    }

    public static MessageResponse changeHost(Long memberId) {
        return new MessageResponse(
                memberId,
                MessageType.CHANGE_HOST,
                null
        );
    }
}
