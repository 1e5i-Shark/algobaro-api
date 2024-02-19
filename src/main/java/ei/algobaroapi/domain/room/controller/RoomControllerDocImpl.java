package ei.algobaroapi.domain.room.controller;

import ei.algobaroapi.domain.room.dto.request.RoomCreateRequestDto;
import ei.algobaroapi.domain.room.dto.request.RoomUpdateRequestDto;
import ei.algobaroapi.domain.room.dto.response.RoomDetailResponseDto;
import ei.algobaroapi.domain.room.dto.response.RoomSubmitCodeResponseDto;
import ei.algobaroapi.domain.room.service.RoomService;
import ei.algobaroapi.global.config.swaggerdoc.RoomControllerDoc;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<RoomDetailResponseDto> getAllRooms() {
        return null;
    }

    @Override
    @PostMapping("/rooms")
    public RoomDetailResponseDto createRoom(
            @RequestBody @Valid RoomCreateRequestDto roomCreateRequestDto) {
        return null;
    }

    @Override
    @PatchMapping("/rooms/{roomId}")
    public RoomDetailResponseDto updateRoomById(@PathVariable(name = "roomId") Long roomId,
            @RequestBody @Valid RoomUpdateRequestDto roomUpdateRequestDto) {
        return null;
    }

    @Override
    @PostMapping("rooms/codes/{roomId}")
    public void startCodingTest(@PathVariable(name = "roomId") Long roomId) {

    }

    @Override
    @GetMapping("/rooms/codes/{roomId}")
    public List<RoomSubmitCodeResponseDto> getSubmitCodesByRoomId(@PathVariable(name = "roomId") Long roomId) {
        return null;
    }
}
