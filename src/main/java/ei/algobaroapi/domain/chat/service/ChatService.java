package ei.algobaroapi.domain.chat.service;

public interface ChatService {

    void enterRoom(String roomShortUuid, Long memberId);

    void quitRoom(String roomShortUuid, Long memberId);

    void convertAndSendMessage(String roomShortUuid, Long memberId, String message);

    void readyRoom(String roomShortUuid, Long memberId);

    void unreadyRoom(String roomShortUuid, Long memberId);

    void changeHostManually(String roomShortUuid, Long beforeHostMemberId, Long afterHostMemberId);

    void startCodingTest(String roomShortUuid, Long memberId);

    void endCodingTest(String roomShortUuid, Long memberId);

    void volume(String roomShortUuid, Long memberId, String message);
}
