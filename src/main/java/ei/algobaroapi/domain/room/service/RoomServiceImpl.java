package ei.algobaroapi.domain.room.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.problem.dto.request.ProblemSolveRequest;
import ei.algobaroapi.domain.problem.service.ProblemService;
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
import ei.algobaroapi.domain.room_member.dto.response.RoomMemberResponseDto;
import ei.algobaroapi.domain.room_member.service.RoomMemberService;
import ei.algobaroapi.domain.solve.domain.SolveHistory;
import ei.algobaroapi.domain.solve.domain.SolveStatus;
import ei.algobaroapi.domain.solve.service.SolveHistoryService;
import ei.algobaroapi.global.dto.PageResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMemberService roomMemberService;
    private final SolveHistoryService solveHistoryService;
    private final ProblemService problemService;

    @Override
    public PageResponse<Room, RoomResponseDto> getAllRooms(RoomListRequestDto request) {
        return PageResponse.of(
                roomRepository.findListPage(
                        request,
                        PageRequest.of(
                                Optional.ofNullable(request.getPage()).orElse(0),
                                Optional.ofNullable(request.getSize()).orElse(6)
                        )
                ),
                RoomResponseDto::of
        );
    }

    @Override
    @Transactional
    public RoomDetailResponseDto createRoom(RoomCreateRequestDto roomCreateRequestDto,
            Member member) {
        Room createdRoom = roomRepository.save(roomCreateRequestDto.toEntity());

        List<RoomMemberResponseDto> roomMembers = roomMemberService.createRoomByRoomId(createdRoom,
                member);

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
    public RoomDetailResponseDto getRoomDetailShortUuid(String roomShortUuid) {
        Room findRoom = getRoomByShortUuid(roomShortUuid);

        return RoomDetailResponseDto.of(findRoom, getRoomMembersByRoomId(findRoom.getId()));
    }

    private Room getRoomByShortUuid(String roomShortUuid) {
        return roomRepository.findByRoomUuidStartingWith(roomShortUuid)
                .orElseThrow(() -> RoomNotFoundException.of(RoomErrorCode.ROOM_NOT_FOUND));
    }

    @Override
    @Transactional
    public RoomDetailResponseDto startCodingTest(String roomShortUuid) {
        Room room = getRoomByShortUuid(roomShortUuid);
        Long roomId = room.getId();

        List<RoomMember> roomMembers = roomMemberService.getByRoomIdAllReady(roomId);

        for (RoomMember roomMember : roomMembers) {
            solveHistoryService.setUpSolveHistory(roomMember.getMember().getId(),
                    roomMember.getRoom().getRoomUuid(), roomMember.getRoom().getProblemLink());
        }

        room.updateRoomStatusRunning();

        return RoomDetailResponseDto.of(room,
                roomMembers.stream().map(RoomMemberResponseDto::of).toList());
    }

    @Override
    @Transactional
    public void completeSolveHistory(String roomShortUuid) {
        List<SolveHistory> solveHistoryList = solveHistoryService.getSolveHistoryListByRoomShortUuid(roomShortUuid);

        solveHistoryList.forEach(solveHistory -> {
            SolveStatus solveStatus = problemService.checkSolveResult(
                    ProblemSolveRequest.builder()
                            .problemLink(solveHistory.getProblemLink())
                            .userBojId(solveHistory.getMember().getBojId())
                            .build()
            );

            solveHistory.complete(solveStatus);
        });
    }

    private List<RoomMemberResponseDto> getRoomMembersByRoomId(Long roomId) {
        return roomMemberService.getRoomMembersByRoomId(roomId);
    }
}
