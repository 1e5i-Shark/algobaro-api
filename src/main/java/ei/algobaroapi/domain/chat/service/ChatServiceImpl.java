package ei.algobaroapi.domain.chat.service;

import ei.algobaroapi.domain.chat.dto.MessageResponse;
import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.service.MemberService;
import ei.algobaroapi.domain.room.dto.response.RoomDetailResponseDto;
import ei.algobaroapi.domain.room.service.RoomService;
import ei.algobaroapi.domain.room_member.dto.request.HostManualChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomExitResponse;
import ei.algobaroapi.domain.room_member.service.RoomMemberService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final MessageService messageService;
    private final MemberService memberService;
    private final RoomService roomService;
    private final RoomMemberService roomMemberService;

    @Override
    public void enterRoom(String roomShortUuid, Long memberId) {
        Member member = memberService.getMemberById(memberId);
        roomMemberService.joinRoomByRoomShortUuid(roomShortUuid, member);
        messageService.sendMessage(
                roomShortUuid,
                MessageResponse.enterRoom(
                        memberId,
                        member.getNickname()
                )
        );
    }

    @Override
    public void quitRoom(String roomShortUuid, Long memberId) {
        Member member = memberService.getMemberById(memberId);
        RoomExitResponse roomExitResponse = roomMemberService.exitRoomByMemberId(memberId);
        if (roomExitResponse.isHostChanged()) {
            messageService.sendMessage(roomShortUuid,
                    MessageResponse.changeHost(roomExitResponse.getNewHostId()));
        }
        messageService.sendMessage(
                roomShortUuid,
                MessageResponse.quitRoom(
                        memberId,
                        member.getNickname()
                )
        );
    }

    @Override
    public void convertAndSendMessage(String roomShortUuid, Long memberId, String message) {
        messageService.sendMessage(roomShortUuid, MessageResponse.sendMessage(memberId, message));
    }

    @Override
    public void readyRoom(String roomShortUuid, Long memberId) {
        roomMemberService.chageStatusToReady(roomShortUuid, memberId);
        messageService.sendMessage(roomShortUuid, MessageResponse.readyRoom(memberId));
    }

    @Override
    public void unreadyRoom(String roomShortUuid, Long memberId) {
        roomMemberService.chageStatusToUnready(roomShortUuid, memberId);
        messageService.sendMessage(roomShortUuid, MessageResponse.unreadyRoom(memberId));
    }

    @Override
    public void changeHostManually(
            String roomShortUuid,
            Long beforeHostMemberId,
            Long afterHostMemberId
    ) {
        roomMemberService.changeHostManually(HostManualChangeRequestDto.builder()
                .roomShortUuid(roomShortUuid)
                .hostMemberId(beforeHostMemberId)
                .organizerMemberId(afterHostMemberId)
                .build()
        );
        messageService.sendMessage(roomShortUuid, MessageResponse.changeHost(afterHostMemberId));
    }

    @Override
    public void startCodingTest(String roomShortUuid, Long memberId) {
        checkMemberIsHost(roomShortUuid, memberId);

        RoomDetailResponseDto roomDetailResponseDto = roomService.startCodingTest(roomShortUuid);
        Integer timeLimitMinute = roomDetailResponseDto.getTimeLimit();
        messageService.sendMessage(
                roomShortUuid,
                MessageResponse.startCoding(
                        memberId,
                        LocalDateTime.now().plusMinutes(timeLimitMinute).toString()
                )
        );
    }

    @Override
    public void endCodingTest(String roomShortUuid, Long memberId) {
        messageService.sendMessage(roomShortUuid, MessageResponse.endCoding(memberId));
    }

    @Override
    public void volume(String roomShortUuid, Long memberId, String message) {
        messageService.sendMessage(roomShortUuid, MessageResponse.volume(memberId, message));
    }

    private void checkMemberIsHost(String roomShortUuid, Long memberId) {
        roomMemberService.validateHost(roomShortUuid, memberId);
    }
}
