package ei.algobaroapi.domain.chat.service;

import ei.algobaroapi.domain.chat.dto.MessageResponse;
import ei.algobaroapi.domain.room_member.service.RoomMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final MessageService messageService;
    private final RoomMemberService roomMemberService;

    @Override
    public void enterRoom(String roomId, Long memberId) {
        messageService.sendMessage(roomId, MessageResponse.enterRoom(memberId));
    }

    @Override
    public void quitRoom(String roomId, Long memberId) {
        roomMemberService.exitRoomByMemberId(memberId);
        messageService.sendMessage(roomId, MessageResponse.quitRoom(memberId));
    }

    @Override
    public void convertAndSendMessage(String roomId, Long memberId, String message) {
        messageService.sendMessage(roomId, MessageResponse.sendMessage(memberId, message));
    }
}
