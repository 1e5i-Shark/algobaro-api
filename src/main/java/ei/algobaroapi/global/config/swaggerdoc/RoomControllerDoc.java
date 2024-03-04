package ei.algobaroapi.global.config.swaggerdoc;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room.dto.request.RoomCreateRequestDto;
import ei.algobaroapi.domain.room.dto.request.RoomListRequestDto;
import ei.algobaroapi.domain.room.dto.request.RoomUpdateRequestDto;
import ei.algobaroapi.domain.room.dto.response.RoomDetailResponseDto;
import ei.algobaroapi.domain.room.dto.response.RoomResponseDto;
import ei.algobaroapi.domain.room.dto.response.RoomSubmitCodeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Room", description = "방 관련 API")
@SuppressWarnings("unused")
public interface RoomControllerDoc {

    @Operation(summary = "모든 방 정보 조회", description = "현재 존재하는 모든 방 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "방 정보 조회 성공")
    List<RoomResponseDto> getAllRooms(RoomListRequestDto roomListRequestDto);

    @Operation(summary = "방 생성", description = "새로운 방을 생성합니다.")
    @ApiResponse(responseCode = "200", description = "방 생성 성공")
    RoomDetailResponseDto createRoom(RoomCreateRequestDto roomCreateRequestDto, Member member);

    @Operation(summary = "방 수정", description = "방 정보를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "방 수정 성공")
    @ApiResponse(responseCode = "E03301", description = "수정하려는 방 정보를 찾지 못했습니다.")
    RoomResponseDto updateRoomById(Long roomId, RoomUpdateRequestDto roomUpdateRequestDto);

    @Operation(summary = "개별 방 정보 조회", description = "short UUID를 통해 방을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "방 정보 조회 성공")
    RoomDetailResponseDto getRoomByShortUuid(String roomShortUuid);

    @Operation(summary = "문제 풀이 시작 - 작업 중", description = "코딩테스트를 시작합니다.")
    @ApiResponse(responseCode = "200", description = "풀이 시작 성공")
    void startCodingTest(Long roomId);

    @Operation(summary = "제출 코드 조회 - 작업 중", description = "해당 방에서 제출한 코드를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "코드 조회 성공")
    List<RoomSubmitCodeResponseDto> getSubmitCodesByRoomId(Long roomId);
}
