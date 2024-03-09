package ei.algobaroapi.domain.chat.service;

import ei.algobaroapi.domain.chat.dto.MessageResponse;
import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.service.MemberService;
import ei.algobaroapi.domain.room_member.dto.request.HostManualChangeRequestDto;
import ei.algobaroapi.domain.room_member.service.RoomMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final MessageService messageService;
    private final MemberService memberService;
    private final RoomMemberService roomMemberService;

    @Override
    public void enterRoom(String roomId, Long memberId) {
        Member member = memberService.getMemberById(memberId);
        roomMemberService.joinRoomByRoomShortUuid(roomId, member);
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

    @Override
    public void readyRoom(String roomId, Long memberId) {
        roomMemberService.chageStatusToReady(roomId, memberId);
        messageService.sendMessage(roomId, MessageResponse.readyRoom(memberId));
    }

    @Override
    public void unreadyRoom(String roomId, Long memberId) {
        roomMemberService.chageStatusToUnready(roomId, memberId);
        messageService.sendMessage(roomId, MessageResponse.unreadyRoom(memberId));
    }

    @Override
    public void changeHostManually(String roomId, Long beforeHostId, Long afterHostId) {
        roomMemberService.changeHostManually(HostManualChangeRequestDto.builder()
                .roomShortUuid(roomId)
                .hostId(beforeHostId)
                .organizerId(afterHostId)
                .build()
        );
        messageService.sendMessage(roomId, MessageResponse.changeHost(beforeHostId));
    }
}
