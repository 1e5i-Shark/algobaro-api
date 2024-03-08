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
import ei.algobaroapi.domain.room_member.exception.RoomMemberNotEnterException;
import ei.algobaroapi.domain.room_member.exception.RoomMemberNotFoundException;
import ei.algobaroapi.domain.room_member.exception.RoomMemberNotReadyException;
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
    public List<RoomMemberResponseDto> joinRoomByRoomId(Long roomId, String password,
            Member member) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> RoomNotFoundException.of(
                RoomErrorCode.ROOM_NOT_FOUND));

        validateConditionToJoinRoom(room, password);

        RoomMember roomMember = RoomMember.builder()
                .room(room)
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
                .orElseThrow(() -> RoomMemberNotFoundException.of(
                        RoomMemberErrorCode.ROOM_MEMBER_ERROR_CODE));

        RoomMember organizer = roomMemberRepository.findById(hostChangeRequestDto.getOrganizerId())
                .orElseThrow(() -> RoomMemberNotFoundException.of(
                        RoomMemberErrorCode.ROOM_MEMBER_ERROR_CODE));

        validateIsHostAndOrganizer(host, organizer);

        host.changeRole(RoomMemberRole.PARTICIPANT);

        organizer.changeRole(RoomMemberRole.HOST);

        return RoomHostResponseDto.of(hostChangeRequestDto.getRoomId(), host, organizer);
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
    public RoomHostResponseDto changeHostAutomatically(RoomMember roomMember) {
        return null;
    }

    @Override
    public List<RoomMember> getByRoomIdAllReady(Long roomId) {
        List<RoomMember> findRoomMembers = roomMemberRepository.findByRoomId(roomId);

        for (RoomMember roomMember : findRoomMembers) {
            if (!checkRoomMemberIsReady(roomMember)) {
                throw RoomMemberNotReadyException.of(RoomMemberErrorCode.ROOM_MEMBER_IS_NOT_READY);
            }
        }

        return findRoomMembers;
    }

    private void validateConditionToJoinRoom(Room room, String password) {
        // 방에 참여할 수 있는지 확인 - 모집 중인지 체크
        checkRoomIsRecruiting(room);

        // 방에 참여할 수 있는지 확인 - 방 비밀번호 체크
        checkRoomPassword(room, password);

        // 방에 참여할 수 있는지 확인 - 인원 수 체크
        checkRoomHeadCount(room);
    }

    private void checkRoomIsRecruiting(Room room) {
        if (!room.isRecruiting()) {
            throw RoomMemberNotEnterException.of(RoomMemberErrorCode.ROOM_MEMBER_CANNOT_ENTER_NOT_RECRUITING);
        }
    }

    private void checkRoomPassword(Room room, String password) {
        if (!room.passwordIsCorrect(password)) {
            throw RoomMemberNotEnterException.of(RoomMemberErrorCode.ROOM_MEMBER_CANNOT_ENTER_PASSWORD);
        }
    }

    private void checkRoomHeadCount(Room room) {
        int roomSize = roomMemberRepository.findByRoomId(room.getId()).size();

        if (room.isHeadCountFull(roomSize)) {
            throw RoomMemberNotFoundException.of(RoomMemberErrorCode.ROOM_MEMBER_CANNOT_ENTER_HEADCOUNT);
        }
    }

    private void validateIsHostAndOrganizer(RoomMember host, RoomMember organizer) {
        if (host.getRoomMemberRole() != RoomMemberRole.HOST) {
            throw HostValidationException.of(RoomMemberErrorCode.ROOM_MEMBER_IS_NOT_HOST);
        }

        if (organizer.getRoomMemberRole() != RoomMemberRole.PARTICIPANT) {
            throw OrganizerValidationException.of(
                    RoomMemberErrorCode.ROOM_MEMBER_IS_NOT_PARTICIPANT);
        }
    }

    private boolean checkRoomMemberIsReady(RoomMember roomMember) {
        return roomMember.isReady();
    }
}
