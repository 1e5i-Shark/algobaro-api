package ei.algobaroapi.domain.room.service;

import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.domain.room.domain.RoomRepository;
import ei.algobaroapi.domain.room.dto.request.RoomCreateRequestDto;
import ei.algobaroapi.domain.room.dto.request.RoomListRequestDto;
import ei.algobaroapi.domain.room.dto.request.RoomUpdateRequestDto;
import ei.algobaroapi.domain.room.dto.response.RoomDetailResponseDto;
import ei.algobaroapi.domain.room.dto.response.RoomSubmitCodeResponseDto;
import ei.algobaroapi.domain.room.exception.RoomNotFoundException;
import ei.algobaroapi.domain.room.exception.common.RoomErrorCode;
import java.util.List;
import java.util.UUID;
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

    @Override
    public List<RoomDetailResponseDto> getAllRooms(RoomListRequestDto roomListRequestDto) {
        Pageable pageable = PageRequest.of(roomListRequestDto.getPage(),
                roomListRequestDto.getSize());

        return roomRepository.findAll(pageable).getContent().stream()
                .map(RoomDetailResponseDto::of)
                .toList();
    }

    @Override
    @Transactional
    public RoomDetailResponseDto createRoom(RoomCreateRequestDto roomCreateRequestDto) {
        return RoomDetailResponseDto.of(roomRepository.save(roomCreateRequestDto.toEntity()));
    }

    @Override
    @Transactional
    public RoomDetailResponseDto updateRoomByRoomId(Long roomId,
            RoomUpdateRequestDto roomUpdateRequestDto) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> RoomNotFoundException.of(RoomErrorCode.ROOM_NOT_FOUND));

        room.update(roomUpdateRequestDto);

        return RoomDetailResponseDto.of(room);
    }

    @Override
    public RoomDetailResponseDto getRoomByRoomUuid(String roomUuid) {
        return RoomDetailResponseDto.of(roomRepository.findByRoomUuidWithRoomMember(
                        UUID.fromString(roomUuid))
                .orElseThrow(() -> RoomNotFoundException.of(RoomErrorCode.ROOM_NOT_FOUND)));
    }

    @Override
    public void startCodingTest(Long roomId) {
        // TODO: RoomStatus Running 으로 변환
    }

    @Override
    public List<RoomSubmitCodeResponseDto> getSubmitCodesByRoomId(Long roomId) {
        // TODO: RoomMember 필드의 submitCode를 List로 반환
        return null;
    }
}
