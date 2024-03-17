package ei.algobaroapi.global.config.swaggerdoc;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.domain.room.dto.request.RoomCreateRequestDto;
import ei.algobaroapi.domain.room.dto.request.RoomListRequestDto;
import ei.algobaroapi.domain.room.dto.request.RoomUpdateRequestDto;
import ei.algobaroapi.domain.room.dto.response.RoomDetailResponseDto;
import ei.algobaroapi.domain.room.dto.response.RoomResponseDto;
import ei.algobaroapi.global.dto.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Room", description = "방 관련 API")
@SuppressWarnings("unused")
public interface RoomControllerDoc {

    @Operation(summary = "모든 방 정보 조회", description = "현재 존재하는 모든 방 정보를 조회합니다.\n\n방 상태: 입장 가능으로 사용 시 RECRUITING, 체크 해제 시 요청 x\n\n방 접근 타입: 비밀방 사용 시 PRIVATE, 체크 해제 시 요청 x)")
    @ApiResponse(responseCode = "200", description = "방 정보 조회 성공")
    PageResponse<Room, RoomResponseDto> getAllRooms(RoomListRequestDto roomListRequestDto);

    @Operation(summary = "방 생성", description = "새로운 방을 생성합니다.")
    @ApiResponse(responseCode = "200", description = "방 생성 성공")
    RoomDetailResponseDto createRoom(RoomCreateRequestDto roomCreateRequestDto, Member member);

    @Operation(summary = "방 수정", description = "방 정보를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "방 수정 성공")
    @ApiResponse(responseCode = "E03301", description = "수정하려는 방 정보를 찾지 못했습니다.")
    RoomResponseDto updateRoomById(String roomShortUuid, RoomUpdateRequestDto roomUpdateRequestDto);

    @Operation(summary = "개별 방 정보 조회", description = "short UUID를 통해 방을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "방 정보 조회 성공")
    RoomDetailResponseDto getRoomByShortUuid(String roomShortUuid);
}
