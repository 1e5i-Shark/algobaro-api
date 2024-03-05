package ei.algobaroapi.domain.chat.controller;

import ei.algobaroapi.domain.chat.dto.MessageRequest;
import ei.algobaroapi.domain.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private static final String CHAT_ROOM_URL = "/chat/room/";
    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    @Value("${spring.myapp.websocket.sub-prefix}")
    private String webSocketSubPrefix;

    @MessageMapping("/chat/enter")
    public void enter(MessageRequest messageRequestDto) {
        simpMessagingTemplate.convertAndSend(
                webSocketSubPrefix + CHAT_ROOM_URL + messageRequestDto.getRoomId(),
                messageService.enterRoom(messageRequestDto.getUserId())
        );
    }

    @MessageMapping("/chat/quit")
    public void quit(MessageRequest messageRequestDto) {
        simpMessagingTemplate.convertAndSend(
                webSocketSubPrefix + CHAT_ROOM_URL + messageRequestDto.getRoomId(),
                messageService.quitRoom(messageRequestDto.getUserId())
        );
    }

    @MessageMapping("/chat/message")
    public void message(MessageRequest messageRequestDto) {
        simpMessagingTemplate.convertAndSend(
                webSocketSubPrefix + CHAT_ROOM_URL + messageRequestDto.getRoomId(),
                messageService.convertAndSendMessage(
                        messageRequestDto.getUserId(),
                        messageRequestDto.getMessage()
                )
        );
    }

    @MessageExceptionHandler
    public String exception() {
        return "Error has occurred.";
    }
}
