package ei.algobaroapi.domain.room.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.domain.room.dto.request.RoomCreateRequestDto;
import ei.algobaroapi.domain.room.dto.request.RoomListRequestDto;
import ei.algobaroapi.domain.room.dto.request.RoomUpdateRequestDto;
import ei.algobaroapi.domain.room.dto.response.RoomDetailResponseDto;
import ei.algobaroapi.domain.room.dto.response.RoomResponseDto;
import ei.algobaroapi.global.dto.PageResponse;

public interface RoomService {

    PageResponse<Room, RoomResponseDto> getAllRooms(RoomListRequestDto roomListRequestDto);

    RoomDetailResponseDto createRoom(RoomCreateRequestDto roomCreateRequestDto, Member member);

    RoomResponseDto updateRoomByRoomId(Long roomId,
            RoomUpdateRequestDto roomUpdateRequestDto);

    RoomDetailResponseDto getRoomDetailShortUuid(String roomUuid);

    RoomDetailResponseDto startCodingTest(String roomUuid);
}
