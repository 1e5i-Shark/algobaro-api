package ei.algobaroapi.domain.room_member.controller;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room_member.domain.RoomMember;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostResponseDto;
import ei.algobaroapi.domain.room_member.service.RoomMemberService;
import ei.algobaroapi.global.config.swaggerdoc.RoomMemberControllerDoc;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RoomMemberControllerDocImpl implements RoomMemberControllerDoc {

    private final RoomMemberService roomMemberService;

    @Override
    @GetMapping("/rooms/{roomId}")
    public void joinRoomByRoomId(@PathVariable(name = "roomId") Long roomId,
            @AuthenticationPrincipal Member member) {

    }

    @Override
    @GetMapping("/rooms/host/{hostId}/{organizerId}")
    public RoomHostResponseDto changeHostManually(@PathVariable(name = "hostId") Long hostId,
            @PathVariable(name = "organizerId") Long organizerId) {
        return null;
    }

    @Override
    @GetMapping("/rooms/host")
    public RoomHostResponseDto changeHostAutomatically(RoomMember roomMember) {
        return null;
    }
}
