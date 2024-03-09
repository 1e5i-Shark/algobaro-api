package ei.algobaroapi.domain.chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private static final String CHAT_ROOM_URL = "/chat/room/";
    private final SimpMessagingTemplate simpMessagingTemplate;
    @Value("${spring.myapp.websocket.sub-prefix}")
    private String webSocketSubPrefix;


    public void sendMessage(String roomId, Object payload) {
        simpMessagingTemplate.convertAndSend(
                webSocketSubPrefix + CHAT_ROOM_URL + roomId,
                payload
        );
    }
}
