package ei.algobaroapi.global.config.swaggerdoc;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room_member.dto.request.HostAutoChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.request.HostManualChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.request.JoinRoomRequestDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostAutoChangeResponseDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostManualResponseDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomMemberResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "RoomMember", description = "방-회원 관련 API")
@SuppressWarnings("unused")
public interface RoomMemberControllerDoc {

    @Operation(summary = "방 참여 전 검증", description = "방 참여 전 검증을 진행합니다.")
    @ApiResponse(responseCode = "200", description = "방 참여 검증에 성공하였습니다. 소켓을 통해 채팅 방 참여 요청을 진행하시면 됩니다.")
    @ApiResponse(responseCode = "E05302", description = "모집 중인 방이 아닙니다.")
    @ApiResponse(responseCode = "E05303", description = "입력한 비밀번호와 방 비밀번호가 일치하지 않습니다.")
    @ApiResponse(responseCode = "E05304", description = "방 참여 가능 인원이 가득 찼습니다.")
    List<RoomMemberResponseDto> validateEnterRoom(String shortUuid, JoinRoomRequestDto joinRoomRequestDto,
            Member member);
}
