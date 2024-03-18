package ei.algobaroapi.domain.room_member.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.domain.room.domain.RoomRepository;
import ei.algobaroapi.domain.room.exception.RoomNotFoundException;
import ei.algobaroapi.domain.room.exception.common.RoomErrorCode;
import ei.algobaroapi.domain.room_member.domain.RoomMember;
import ei.algobaroapi.domain.room_member.domain.RoomMemberRepository;
import ei.algobaroapi.domain.room_member.domain.RoomMemberRole;
import ei.algobaroapi.domain.room_member.dto.request.HostAutoChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.request.HostManualChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomExitResponse;
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
    public void joinRoomByRoomShortUuid(String shortUuid, Member member) {
        Room room = roomRepository.findByRoomUuidStartingWith(shortUuid)
                .orElseThrow(() -> RoomNotFoundException.of(RoomErrorCode.ROOM_NOT_FOUND));

        // 이미 방에 존재하면 아무것도 안 함
        if (roomMemberRepository
                .findRoomMemberByRoomIdAndMemberId(room.getId(), member.getId())
                .isPresent()) {
            return;
        }

        RoomMember roomMember = RoomMember.builder()
                .room(room)
                .member(member)
                .roomMemberRole(RoomMemberRole.PARTICIPANT)
                .isReady(false)
                .build();

        roomMemberRepository.save(roomMember);
    }

    @Override
    @Transactional
    public List<RoomMemberResponseDto> validateEnterRoom(String shortUuid, String password,
            Member member) {
        Room room = roomRepository.findByRoomUuidStartingWith(shortUuid)
                .orElseThrow(() -> RoomNotFoundException.of(RoomErrorCode.ROOM_NOT_FOUND));

        // 만약 방에 이미 Member가 존재하면(재참가) validate를 하지 않고 참여하도록 함
        if (roomMemberRepository
                .findRoomMemberByRoomIdAndMemberId(room.getId(), member.getId())
                .isEmpty()) {
            validateConditionToJoinRoom(room, password);
        }

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
    public RoomMemberResponseDto chageStatusToReady(String roomShortUuid, Long memberId) {
        Room room = roomRepository.findByRoomUuidStartingWith(roomShortUuid)
                .orElseThrow(() -> RoomNotFoundException.of(RoomErrorCode.ROOM_NOT_FOUND));
        RoomMember roomMember = roomMemberRepository.findRoomMemberByRoomIdAndMemberId(room.getId(),
                        memberId)
                .orElseThrow(() -> RoomMemberNotFoundException.of(
                        RoomMemberErrorCode.ROOM_MEMBER_ERROR_CODE));

        roomMember.markReady();

        return RoomMemberResponseDto.of(roomMember);
    }

    @Override
    @Transactional
    public RoomMemberResponseDto chageStatusToUnready(String roomShortUuid, Long memberId) {
        Room room = roomRepository.findByRoomUuidStartingWith(roomShortUuid)
                .orElseThrow(() -> RoomNotFoundException.of(RoomErrorCode.ROOM_NOT_FOUND));
        RoomMember roomMember = roomMemberRepository.findRoomMemberByRoomIdAndMemberId(room.getId(),
                        memberId)
                .orElseThrow(() -> RoomMemberNotFoundException.of(
                        RoomMemberErrorCode.ROOM_MEMBER_ERROR_CODE));

        roomMember.markUnready();

        return RoomMemberResponseDto.of(roomMember);
    }

    @Override
    @Transactional
    public RoomHostManualResponseDto changeHostManually(
            HostManualChangeRequestDto hostManualChangeRequestDto) {
        Room room = roomRepository.findByRoomUuidStartingWith(
                        hostManualChangeRequestDto.getRoomShortUuid())
                .orElseThrow(() -> RoomNotFoundException.of(RoomErrorCode.ROOM_NOT_FOUND));
        RoomMember host = roomMemberRepository.findRoomMemberByRoomIdAndMemberId(room.getId(),
                        hostManualChangeRequestDto.getHostMemberId())
                .orElseThrow(() -> RoomMemberNotFoundException.of(
                        RoomMemberErrorCode.ROOM_MEMBER_ERROR_CODE));

        RoomMember organizer = roomMemberRepository.findRoomMemberByRoomIdAndMemberId(room.getId(),
                        hostManualChangeRequestDto.getOrganizerMemberId())
                .orElseThrow(() -> RoomMemberNotFoundException.of(
                        RoomMemberErrorCode.ROOM_MEMBER_ERROR_CODE));

        validateIsHostAndOrganizer(host, organizer);

        host.changeRoleToParticipant();

        organizer.changeRoleToHost();

        return RoomHostManualResponseDto.of(hostManualChangeRequestDto.getRoomShortUuid(), host,
                organizer);
    }

    // 현재 내부 호출 당하고 있어 Transactional을 붙이지 않음
    @Override
    public RoomHostAutoChangeResponseDto changeHostAutomatically(
            HostAutoChangeRequestDto hostAutoChangeRequestDto) {
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
    public RoomExitResponse exitRoomByMemberId(Long memberId) {
        Room findRoom = roomMemberRepository.findRoomByMemberId(memberId)
                .orElseThrow(() -> RoomNotFoundException.of(RoomErrorCode.ROOM_NOT_FOUND));

        RoomMember roomMember = roomMemberRepository.findRoomMemberByRoomIdAndMemberId(
                        findRoom.getId(), memberId)
                .orElseThrow(() -> RoomMemberNotFoundException.of(
                        RoomMemberErrorCode.ROOM_MEMBER_ERROR_CODE));

        // 진행 중인 방인 경우 나가더라도 재입장을 위해 삭제하지 않음
        if (!findRoom.isRunning()) {
            roomMemberRepository.delete(roomMember);
        }

        // 만약 방이 빈 방이 되었을 경우 방을 삭제
        if (checkRoomMemberExistInRoom(findRoom)) {
            roomRepository.delete(findRoom);
        } else {
            // 방이 비지 않으면서 방장이 나갔을 경우 방장을 변경
            if (roomMember.isHost()) {
                RoomHostAutoChangeResponseDto roomHostAutoChangeResponseDto = this.changeHostAutomatically(
                        HostAutoChangeRequestDto
                                .builder()
                                .roomId(findRoom.getId())
                                .build()
                );
                return RoomExitResponse.changedHost(
                        findRoom.getId(),
                        roomHostAutoChangeResponseDto.getNewHostId()
                );
            }
        }

        return RoomExitResponse.notChangedHost(findRoom.getId());
    }

    @Override
    public void validateHost(String roomShortUuid, Long memberId) {
        Room room = roomRepository.findByRoomUuidStartingWith(roomShortUuid)
                .orElseThrow(() -> RoomNotFoundException.of(RoomErrorCode.ROOM_NOT_FOUND));

        RoomMember roomMember = roomMemberRepository.findRoomMemberByRoomIdAndMemberId(room.getId(),
                        memberId)
                .orElseThrow(() -> RoomMemberNotFoundException.of(
                        RoomMemberErrorCode.ROOM_MEMBER_ERROR_CODE));

        if (!roomMember.isHost()) {
            throw HostValidationException.of(RoomMemberErrorCode.ROOM_MEMBER_IS_NOT_HOST);
        }
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
