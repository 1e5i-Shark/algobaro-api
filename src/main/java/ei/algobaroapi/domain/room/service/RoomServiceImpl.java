package ei.algobaroapi.domain.room.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.service.MemberService;
import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.domain.room.domain.RoomRepository;
import ei.algobaroapi.domain.room.dto.request.RoomCreateRequestDto;
import ei.algobaroapi.domain.room.dto.request.RoomListRequestDto;
import ei.algobaroapi.domain.room.dto.request.RoomUpdateRequestDto;
import ei.algobaroapi.domain.room.dto.response.RoomDetailResponseDto;
import ei.algobaroapi.domain.room.dto.response.RoomResponseDto;
import ei.algobaroapi.domain.room.exception.RoomNotFoundException;
import ei.algobaroapi.domain.room.exception.common.RoomErrorCode;
import ei.algobaroapi.domain.room_member.domain.RoomMember;
import ei.algobaroapi.domain.room_member.domain.RoomMemberRepository;
import ei.algobaroapi.domain.room_member.dto.response.RoomMemberResponseDto;
import ei.algobaroapi.domain.room_member.service.RoomMemberService;
import ei.algobaroapi.domain.solve.domain.SolveHistory;
import ei.algobaroapi.domain.solve.domain.SolveHistoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMemberService roomMemberService;

    private final MemberService memberService;
    private final SolveHistoryRepository solveHistoryRepository;
    private final RoomMemberRepository roomMemberRepository;

    @Override
    public List<RoomResponseDto> getAllRooms(RoomListRequestDto roomListRequestDto) {
        Pageable pageable = PageRequest.of(roomListRequestDto.getPage(),
                roomListRequestDto.getSize());

        return roomRepository.findAll(pageable).getContent().stream()
                .map(RoomResponseDto::of)
                .toList();
    }

    @Override
    @Transactional
    public RoomDetailResponseDto createRoom(RoomCreateRequestDto roomCreateRequestDto,
            Member member) {
        Room createdRoom = roomRepository.save(roomCreateRequestDto.toEntity()); // DB 방 생성

        List<RoomMemberResponseDto> roomMembers = roomMemberService.createRoomByRoomId(createdRoom,
                member);// DB RoomMember 방장 정보 생성

        return RoomDetailResponseDto.of(createdRoom, roomMembers);
    }

    @Override
    @Transactional
    public RoomResponseDto updateRoomByRoomId(Long roomId,
            RoomUpdateRequestDto roomUpdateRequestDto) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> RoomNotFoundException.of(RoomErrorCode.ROOM_NOT_FOUND));

        room.update(roomUpdateRequestDto);

        return RoomResponseDto.of(room);
    }

    @Override
    public RoomDetailResponseDto getRoomByRoomUuid(String roomShortUuid) {
        Room findRoom = roomRepository.findByRoomUuidStartingWith(roomShortUuid)
                .orElseThrow(() -> RoomNotFoundException.of(RoomErrorCode.ROOM_NOT_FOUND));

        return RoomDetailResponseDto.of(findRoom, getRoomMembersByRoomId(findRoom.getId()));
    }

    @Override
    @Transactional
    public RoomDetailResponseDto startCodingTest(String roomShortUuid) {
        // Room 찾기
        Room room = roomRepository.findByRoomUuidStartingWith(roomShortUuid)
                .orElseThrow(() -> RoomNotFoundException.of(RoomErrorCode.ROOM_NOT_FOUND));

        Long roomId = room.getId();

//        // RoomMember 찾기
        List<RoomMember> findRoomMembers = roomMemberRepository.findByRoomId(roomId);

        // RoomMember 들이 준비 상태 인지 확인
        for (RoomMember roomMember : findRoomMembers) {
            if (!checkRoomMemberIsReady(roomMember)) {
                throw RoomNotFoundException.of(RoomErrorCode.ROOM_NOT_READY);
            }
        }

        // SolveHistory 에 RoomMember 정보 저장
        for (RoomMember roomMember : findRoomMembers) {
            SolveHistory createSolveHistory = SolveHistory.builder()
                    .member(memberService.getMemberById(
                            roomMember.getMember().getId()))
                    .roomUuid(roomShortUuid)
                    .problemLink(room.getProblemLink())
                    .build();

            solveHistoryRepository.save(createSolveHistory);
        }

        // 방 상태 변경
        room.updateRoomStatusRunning();

        // 방 정보 반환
        return RoomDetailResponseDto.of(room,
                findRoomMembers.stream().map(RoomMemberResponseDto::of).toList());
    }

    private boolean checkRoomMemberIsReady(RoomMember roomMember) {
        return roomMember.isReady();
    }

    private List<RoomMemberResponseDto> getRoomMembersByRoomId(Long roomId) {
        return roomMemberService.getRoomMembersByRoomId(roomId);
    }
}
