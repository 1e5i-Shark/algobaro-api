package ei.algobaroapi.domain.room.controller;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.domain.room.dto.request.RoomCreateRequestDto;
import ei.algobaroapi.domain.room.dto.request.RoomListRequestDto;
import ei.algobaroapi.domain.room.dto.request.RoomUpdateRequestDto;
import ei.algobaroapi.domain.room.dto.response.RoomDetailResponseDto;
import ei.algobaroapi.domain.room.dto.response.RoomResponseDto;
import ei.algobaroapi.domain.room.service.RoomService;
import ei.algobaroapi.global.config.swaggerdoc.RoomControllerDoc;
import ei.algobaroapi.global.dto.PageResponse;
import jakarta.validation.Valid;
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
    public PageResponse<Room, RoomResponseDto> getAllRooms(
            @ModelAttribute @Valid RoomListRequestDto roomListRequestDto) {
        return roomService.getAllRooms(roomListRequestDto);
    }

    @Override
    @PostMapping("/rooms")
    @PreAuthorize("hasRole('USER')")
    public RoomDetailResponseDto createRoom(
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
    public RoomDetailResponseDto getRoomByShortUuid(
            @PathVariable(name = "roomShortUuid") String roomShortUuid) {
        return roomService.getRoomDetailShortUuid(roomShortUuid);
    }

    @Override
    @PostMapping("rooms/codes/{roomShortUuid}")
    public RoomDetailResponseDto startCodingTest(
            @PathVariable(name = "roomShortUuid") String roomShortUuid
    ) {
        return roomService.startCodingTest(roomShortUuid);
    }
}
