package ei.algobaroapi.global.config.swaggerdoc;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room_member.dto.request.HostAutoChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.request.HostChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.request.JoinRoomRequestDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostAutoChangeResponseDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostResponseDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomMemberResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "RoomMember", description = "방-회원 관련 API")
@SuppressWarnings("unused")
public interface RoomMemberControllerDoc {

    @Operation(summary = "방 참여", description = "생성된 방에 참여합니다.")
    @ApiResponse(responseCode = "200", description = "방 참여에 성공하였습니다.")
    @ApiResponse(responseCode = "E05302", description = "모집 중인 방이 아닙니다.")
    @ApiResponse(responseCode = "E05303", description = "입력한 비밀번호와 방 비밀번호가 일치하지 않습니다.")
    @ApiResponse(responseCode = "E05304", description = "방 참여 가능 인원이 가득 찼습니다.")
    List<RoomMemberResponseDto> joinRoomByRoomId(Long roomId, JoinRoomRequestDto joinRoomRequestDto,
            Member member);

    @Operation(summary = "준비 상태 변경", description = "방 참여자의 준비 상태를 변경합니다. true -> false, false -> true")
    @ApiResponse(responseCode = "200", description = "준비 상태 변경에 성공하였습니다.")
    @ApiResponse(responseCode = "E05301", description = "해당 방에 멤버를 찾지 못했습니다.")
    RoomMemberResponseDto changeReadyStatus(Long roomId, Member member);

    @Operation(summary = "방장 수동 변경", description = "현재 방장이 참여자에게 방장 권한을 위임합니다.")
    @ApiResponse(responseCode = "200", description = "방장 수동 위임에 성공하였습니다.")
    @ApiResponse(responseCode = "E05301", description = "해당 방에 멤버를 찾지 못했습니다.")
    @ApiResponse(responseCode = "E05302", description = "방장 권한을 위임할 수 있는 권한을 가지고 있지 않습니다.")
    @ApiResponse(responseCode = "E05303", description = "방장 권한을 위임 받을 수 있는 참여자가 아닙니다.")
    RoomHostResponseDto changeHostManually(HostChangeRequestDto hostChangeRequestDto);

    @Operation(summary = "방장 자동 변경", description = "현재 방장이 방을 나갔을 경우, 방에 참여한 순으로 방장을 새로 위임합니다.")
    @ApiResponse(responseCode = "200", description = "방장 자동 위임에 성공하였습니다.")
    RoomHostAutoChangeResponseDto changeHostAutomatically(HostAutoChangeRequestDto hostAutoChangeRequestDto);
}
