package ei.algobaroapi.domain.room_member.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.domain.room.domain.RoomRepository;
import ei.algobaroapi.domain.room.exception.RoomNotFoundException;
import ei.algobaroapi.domain.room.exception.common.RoomErrorCode;
import ei.algobaroapi.domain.room_member.domain.RoomMember;
import ei.algobaroapi.domain.room_member.domain.RoomMemberRepository;
import ei.algobaroapi.domain.room_member.domain.RoomMemberRole;
import ei.algobaroapi.domain.room_member.dto.request.HostChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostResponseDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomMemberResponseDto;
import ei.algobaroapi.domain.room_member.exception.HostValidationException;
import ei.algobaroapi.domain.room_member.exception.OrganizerValidationException;
import ei.algobaroapi.domain.room_member.exception.common.RoomMemberErrorCode;
import jakarta.persistence.EntityNotFoundException;
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
    public RoomHostResponseDto changeHostManually(HostChangeRequestDto hostChangeRequestDto) {

        RoomMember host = roomMemberRepository.findById(hostChangeRequestDto.getHostId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "해당 방에 참여한 회원이 없습니다.")); // TODO: 만들어 놓은 예외로 변경

        RoomMember organizer = roomMemberRepository.findById(hostChangeRequestDto.getOrganizerId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "해당 방에 참여한 회원이 없습니다.")); // TODO: 만들어 놓은 예외로 변경

        validateIsHostAndOrganizer(host, organizer);

        host.changeRole(RoomMemberRole.PARTICIPANT);

        organizer.changeRole(RoomMemberRole.HOST);

        return RoomHostResponseDto.of(hostChangeRequestDto.getRoomId(),host, organizer);
    }

    @Override
    public RoomHostResponseDto changeHostAutomatically(RoomMember roomMember) {
        return null;
    }

    private void validateIsHostAndOrganizer(RoomMember host, RoomMember organizer) {
        if (host.getRoomMemberRole() != RoomMemberRole.HOST) {
            throw HostValidationException.of(RoomMemberErrorCode.ROOM_MEMBER_IS_NOT_HOST);
        }

        if (organizer.getRoomMemberRole() != RoomMemberRole.PARTICIPANT) {
            throw OrganizerValidationException.of(RoomMemberErrorCode.ROOM_MEMBER_IS_NOT_PARTICIPANT);
        }
    }
}
