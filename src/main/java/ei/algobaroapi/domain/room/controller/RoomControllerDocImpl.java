package ei.algobaroapi.domain.room.controller;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room.dto.request.RoomCreateRequestDto;
import ei.algobaroapi.domain.room.dto.request.RoomListRequestDto;
import ei.algobaroapi.domain.room.dto.request.RoomUpdateRequestDto;
import ei.algobaroapi.domain.room.dto.response.RoomResponseDto;
import ei.algobaroapi.domain.room.dto.response.RoomSubmitCodeResponseDto;
import ei.algobaroapi.domain.room.service.RoomService;
import ei.algobaroapi.global.config.swaggerdoc.RoomControllerDoc;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RoomControllerDocImpl implements RoomControllerDoc {

    private final RoomService roomService;

    @Override
    @GetMapping("/rooms")
    public List<RoomResponseDto> getAllRooms(
            @ModelAttribute @Valid RoomListRequestDto roomListRequestDto) {
        return roomService.getAllRooms(roomListRequestDto);
    }

    @Override
    @PostMapping("/rooms")
    @PreAuthorize("hasRole('USER')")
    public RoomResponseDto createRoom(
            @RequestBody @Valid RoomCreateRequestDto roomCreateRequestDto,
            @AuthenticationPrincipal Member member) {
        return roomService.createRoom(roomCreateRequestDto, member);
    }

    @Override
    @PatchMapping("/rooms/{roomId}")
    public RoomResponseDto updateRoomById(@PathVariable(name = "roomId") Long roomId,
            @RequestBody @Valid RoomUpdateRequestDto roomUpdateRequestDto) {
        return roomService.updateRoomByRoomId(roomId, roomUpdateRequestDto);
    }

    @Override
    @GetMapping("/rooms/{roomShortUuid}")
    public RoomResponseDto getRoomByShortUuid(
            @PathVariable(name = "roomShortUuid") String roomShortUuid) {
        return roomService.getRoomByRoomUuid(roomShortUuid);
    }

    @Override
    @PostMapping("rooms/codes/{roomId}")
    public void startCodingTest(@PathVariable(name = "roomId") Long roomId) {

    }

    @Override
    @GetMapping("/rooms/codes/{roomId}")
    public List<RoomSubmitCodeResponseDto> getSubmitCodesByRoomId(
            @PathVariable(name = "roomId") Long roomId) {
        return null;
    }
}
