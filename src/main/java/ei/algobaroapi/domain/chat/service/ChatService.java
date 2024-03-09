package ei.algobaroapi.domain.chat.service;

import ei.algobaroapi.domain.chat.dto.MessageResponse;

public interface ChatService {

    MessageResponse enterRoom(String userId);

    MessageResponse quitRoom(String userId);

    MessageResponse convertAndSendMessage(String userId, String message);
}
