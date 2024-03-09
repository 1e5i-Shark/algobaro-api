package ei.algobaroapi.domain.room_member.controller;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room_member.dto.request.HostAutoChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.request.HostChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.request.JoinRoomRequestDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostResponseDto;
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
    @PostMapping("/rooms-join/{roomId}")
    @PreAuthorize("hasRole('USER')")
    public List<RoomMemberResponseDto> joinRoomByRoomId(@PathVariable(name = "roomId") Long roomId,
            @RequestBody JoinRoomRequestDto joinRoomRequestDto,
            @AuthenticationPrincipal Member member) {
        return roomMemberService.joinRoomByRoomId(roomId, joinRoomRequestDto.getPassword(), member);
    }

    @PostMapping("/rooms-ready/{roomId}")
    @PreAuthorize("hasRole('USER')")
    public RoomMemberResponseDto changeReadyStatus(@PathVariable(name = "roomId") Long roomId,
            @AuthenticationPrincipal Member member) {
        return roomMemberService.changeReadyStatus(roomId, member.getId());
    }

    @Override
    @PostMapping("/rooms/manual-change-host")
    public RoomHostResponseDto changeHostManually(
            @RequestBody HostChangeRequestDto hostChangeRequestDto) {
        return roomMemberService.changeHostManually(hostChangeRequestDto);
    }

    @Override
    @PostMapping("/rooms/auto-change-host")
    public RoomHostDto changeHostAutomatically(
            @RequestBody HostAutoChangeRequestDto hostAutoChangeRequestDto) {
        return roomMemberService.changeHostAutomatically(hostAutoChangeRequestDto);
    }
}
