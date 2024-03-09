package ei.algobaroapi.domain.chat.service;

public interface ChatService {

    void enterRoom(String roomId, Long memberId);

    void quitRoom(String roomId, Long memberId);

    void convertAndSendMessage(String roomId, Long memberId, String message);
}
