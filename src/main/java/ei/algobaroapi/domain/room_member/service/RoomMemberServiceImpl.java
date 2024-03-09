package ei.algobaroapi.domain.room_member.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.domain.room.domain.RoomRepository;
import ei.algobaroapi.domain.room.exception.RoomNotFoundException;
import ei.algobaroapi.domain.room.exception.common.RoomErrorCode;
import ei.algobaroapi.domain.room.service.RoomService;
import ei.algobaroapi.domain.room_member.domain.RoomMember;
import ei.algobaroapi.domain.room_member.domain.RoomMemberRepository;
import ei.algobaroapi.domain.room_member.domain.RoomMemberRole;
import ei.algobaroapi.domain.room_member.dto.request.HostAutoChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.request.HostManualChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostAutoChangeResponseDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostManualResponseDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomMemberResponseDto;
import ei.algobaroapi.domain.room_member.exception.HostValidationException;
import ei.algobaroapi.domain.room_member.exception.OrganizerValidationException;
import ei.algobaroapi.domain.room_member.exception.RoomMemberNotEnterException;
import ei.algobaroapi.domain.room_member.exception.RoomMemberNotFoundException;
import ei.algobaroapi.domain.room_member.exception.RoomMemberNotReadyException;
import ei.algobaroapi.domain.room_member.exception.common.RoomMemberErrorCode;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomMemberServiceImpl implements RoomMemberService {

    private final RoomRepository roomRepository;
    private final RoomService roomService;
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
    public List<RoomMemberResponseDto> joinRoomByRoomShortUuid(String shortUuid, String password,
            Member member) {
        Room room = roomService.getRoomByShortUuid(shortUuid);

        validateConditionToJoinRoom(room, password);

        RoomMember roomMember = RoomMember.builder()
                .room(room)
                .member(member)
                .roomMemberRole(RoomMemberRole.PARTICIPANT)
                .isReady(false)
                .build();

        roomMemberRepository.save(roomMember);

        return roomMemberRepository.findByRoomId(room.getId()).stream()
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
    @Transactional
    public RoomHostManualResponseDto changeHostManually(
            HostManualChangeRequestDto hostManualChangeRequestDto) {
        RoomMember host = roomMemberRepository.findById(hostManualChangeRequestDto.getHostId())
                .orElseThrow(() -> RoomMemberNotFoundException.of(
                        RoomMemberErrorCode.ROOM_MEMBER_ERROR_CODE));

        RoomMember organizer = roomMemberRepository.findById(hostManualChangeRequestDto.getOrganizerId())
                .orElseThrow(() -> RoomMemberNotFoundException.of(
                        RoomMemberErrorCode.ROOM_MEMBER_ERROR_CODE));

        validateIsHostAndOrganizer(host, organizer);

        host.changeRoleToParticipant();

        organizer.changeRoleToHost();

        return RoomHostManualResponseDto.of(hostManualChangeRequestDto.getRoomId(), host, organizer);
    }

    @Override
    @Transactional
    public RoomHostAutoChangeResponseDto changeHostAutomatically(HostAutoChangeRequestDto hostAutoChangeRequestDto) {
        Long roomId = hostAutoChangeRequestDto.getRoomId();

        RoomMember newHost = roomMemberRepository.findByRoomId(roomId).stream()
                .filter(RoomMember::isParticipant)
                .min(Comparator.comparing(RoomMember::getCreatedAt))
                .orElseThrow(() -> RoomMemberNotFoundException.of(
                        RoomMemberErrorCode.ROOM_MEMBER_ERROR_CODE));

        newHost.changeRoleToHost();

        return RoomHostAutoChangeResponseDto.of(roomId, newHost);
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

    @Override
    @Transactional
    public List<RoomMemberResponseDto> exitRoomByMemberId(Long memberId) {
        Room findRoom = roomMemberRepository.findRoomByMemberId(memberId)
                .orElseThrow(() -> RoomNotFoundException.of(RoomErrorCode.ROOM_NOT_FOUND));

        RoomMember roomMember = roomMemberRepository.findRoomMemberByRoomIdAndMemberId(
                        findRoom.getId(), memberId)
                .orElseThrow(() -> RoomMemberNotFoundException.of(
                        RoomMemberErrorCode.ROOM_MEMBER_ERROR_CODE));

        roomMemberRepository.delete(roomMember);

        if (checkRoomMemberExistInRoom(findRoom)) {
            roomRepository.delete(findRoom);
        }

        return roomMemberRepository.findByRoomId(findRoom.getId()).stream()
                .map(RoomMemberResponseDto::of)
                .toList();
    }

    private void validateConditionToJoinRoom(Room room, String password) {
        checkRoomIsRecruiting(room);

        checkRoomPassword(room, password);

        checkRoomHeadCount(room);
    }

    private void checkRoomIsRecruiting(Room room) {
        if (!room.isRecruiting()) {
            throw RoomMemberNotEnterException.of(
                    RoomMemberErrorCode.ROOM_MEMBER_CANNOT_ENTER_NOT_RECRUITING);
        }
    }

    private void checkRoomPassword(Room room, String password) {
        if (!room.passwordIsCorrect(password)) {
            throw RoomMemberNotEnterException.of(
                    RoomMemberErrorCode.ROOM_MEMBER_CANNOT_ENTER_PASSWORD);
        }
    }

    private void checkRoomHeadCount(Room room) {
        int roomSize = roomMemberRepository.findByRoomId(room.getId()).size();

        if (room.isHeadCountFull(roomSize)) {
            throw RoomMemberNotFoundException.of(
                    RoomMemberErrorCode.ROOM_MEMBER_CANNOT_ENTER_HEADCOUNT);
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

    private boolean checkRoomMemberExistInRoom(Room findRoom) {
        return roomMemberRepository.findByRoomId(findRoom.getId()).isEmpty();
    }
}
