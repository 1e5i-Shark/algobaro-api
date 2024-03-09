package ei.algobaroapi.domain.room_member.controller;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room_member.dto.request.HostAutoChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.request.HostManualChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.request.JoinRoomRequestDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostAutoChangeResponseDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostManualResponseDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomMemberResponseDto;
import ei.algobaroapi.domain.room_member.service.RoomMemberService;
import ei.algobaroapi.global.config.swaggerdoc.RoomMemberControllerDoc;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RoomMemberControllerDocImpl implements RoomMemberControllerDoc {

    private final RoomMemberService roomMemberService;

    @Override
    @PostMapping("/rooms/{shortUuid}/validate-enter")
    @PreAuthorize("hasRole('USER')")
    public List<RoomMemberResponseDto> validateEnterRoom(@PathVariable(name = "shortUuid") String shortUuid,
            @RequestBody JoinRoomRequestDto joinRoomRequestDto,
            @AuthenticationPrincipal Member member) {
        return roomMemberService.validateEnterRoom(shortUuid, joinRoomRequestDto.getPassword(), member);
    }

    @Override
    @PostMapping("/rooms/manual-change-host")
    public RoomHostManualResponseDto changeHostManually(
            @RequestBody HostManualChangeRequestDto hostManualChangeRequestDto) {
        return roomMemberService.changeHostManually(hostManualChangeRequestDto);
    }

    @Override
    @PostMapping("/rooms/auto-change-host")
    public RoomHostAutoChangeResponseDto changeHostAutomatically(
            @RequestBody HostAutoChangeRequestDto hostAutoChangeRequestDto) {
        return roomMemberService.changeHostAutomatically(hostAutoChangeRequestDto);
    }
}
