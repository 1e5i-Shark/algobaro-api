package ei.algobaroapi.domain.chat.service;

import ei.algobaroapi.domain.chat.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    @Override
    public MessageResponse enterRoom(String userId) {
        return MessageResponse.enterRoom(userId);
    }

    @Override
    public MessageResponse quitRoom(String userId) {
        return MessageResponse.quitRoom(userId);
    }

    @Override
    public MessageResponse convertAndSendMessage(String userId, String message) {
        return MessageResponse.sendMessage(userId, message);
    }
}
