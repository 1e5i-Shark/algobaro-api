package ei.algobaroapi.domain.room_member.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.domain.room.domain.RoomRepository;
import ei.algobaroapi.domain.room.exception.RoomNotFoundException;
import ei.algobaroapi.domain.room.exception.common.RoomErrorCode;
import ei.algobaroapi.domain.room_member.domain.RoomMember;
import ei.algobaroapi.domain.room_member.domain.RoomMemberRepository;
import ei.algobaroapi.domain.room_member.domain.RoomMemberRole;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostResponseDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomMemberResponseDto;
import ei.algobaroapi.domain.room_member.exception.RoomMemberNotFoundException;
import ei.algobaroapi.domain.room_member.exception.common.RoomMemberErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomMemberServiceImpl implements RoomMemberService {

    private final RoomRepository roomRepository;
    private final RoomMemberRepository roomMemberRepository;

    @Override
    @Transactional
    public List<RoomMemberResponseDto> createRoomByRoomId(Room room, Member member) {
        RoomMember roomMember = RoomMember.builder()
                .room(room)
                .member(member)
                .roomMemberRole(RoomMemberRole.HOST)
                .isReady(true)
                .build();

        roomMemberRepository.save(roomMember);

        return roomMemberRepository.findByRoomId(room.getId()).stream()
                .map(RoomMemberResponseDto::of)
                .toList();
    }

    @Override
    @Transactional
    public List<RoomMemberResponseDto> joinRoomByRoomId(Long roomId, Member member) {
        RoomMember roomMember = RoomMember.builder()
                .room(roomRepository.findById(roomId).orElseThrow(() -> RoomNotFoundException.of(
                        RoomErrorCode.ROOM_NOT_FOUND)))
                .member(member)
                .roomMemberRole(RoomMemberRole.PARTICIPANT)
                .isReady(false)
                .build();

        roomMemberRepository.save(roomMember);

        return roomMemberRepository.findByRoomId(roomId).stream()
                .map(RoomMemberResponseDto::of)
                .toList();
    }

    @Override
    public List<RoomMemberResponseDto> getRoomMembersByRoomId(Long roomId) {
        return roomMemberRepository.findByRoomId(roomId).stream()
                .map(RoomMemberResponseDto::of)
                .toList();
    }

    @Override
    @Transactional
    public RoomMemberResponseDto changeReadyStatus(Long roomId, Long memberId) {
        RoomMember roomMember = roomMemberRepository.findRoomMemberByRoomIdAndMemberId(roomId,
                        memberId)
                .orElseThrow(() -> RoomMemberNotFoundException.of(
                        RoomMemberErrorCode.ROOM_MEMBER_ERROR_CODE));

        roomMember.changeReadyStatus();

        return RoomMemberResponseDto.of(roomMember);
    }

    @Override
    public RoomHostResponseDto changeHostManually(Long hostId, Long organizerId) {
        return null;
    }

    @Override
    public RoomHostResponseDto changeHostAutomatically(RoomMember roomMember) {
        return null;
    }
}
