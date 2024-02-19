package ei.algobaroapi.global.config.swaggerdoc;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room_member.domain.RoomMember;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "RoomMember", description = "방-회원 관련 API")
@SuppressWarnings("unused")
public interface RoomMemberControllerDoc {

    @Operation(summary = "방 참여 - 작업 중", description = "생성된 방에 참여합니다.")
    @ApiResponse(responseCode = "200", description = "방 참여에 성공하였습니다.")
    void joinRoomByRoomId(Long roomId, Member member);

    @Operation(summary = "방장 수동 변경 - 작업 중", description = "현재 방장이 참여자에게 방장 권한을 위임합니다.")
    @ApiResponse(responseCode = "200", description = "방장 수동 위임에 성공하였습니다.")
    RoomHostResponseDto changeHostManually(Long hostId, Long organizerId);

    @Operation(summary = "방장 자동 변경 - 작업 중", description = "현재 방장이 방을 나갔을 경우, 방에 참여한 순으로 방장을 새로 위임합니다.")
    @ApiResponse(responseCode = "200", description = "방장 자동 위임에 성공하였습니다.")
    RoomHostResponseDto changeHostAutomatically(RoomMember roomMember);
}