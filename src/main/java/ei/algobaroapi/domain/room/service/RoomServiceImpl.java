package ei.algobaroapi.domain.room.service;

import ei.algobaroapi.domain.room.dto.request.RoomCreateRequestDto;
import ei.algobaroapi.domain.room.dto.request.RoomUpdateRequestDto;
import ei.algobaroapi.domain.room.dto.response.RoomDetailResponseDto;
import ei.algobaroapi.domain.room.dto.response.RoomSubmitCodeResponseDto;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RoomServiceImpl implements RoomService {

    @Override
    public List<RoomDetailResponseDto> getAllRooms() {
        return null;
    }

    @Override
    public RoomDetailResponseDto createRoom(RoomCreateRequestDto roomCreateRequestDto) {
        return null;
    }

    @Override
    public RoomDetailResponseDto updateRoomByRoomId(Long roomId, RoomUpdateRequestDto roomUpdateRequestDto) {
        return null;
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
