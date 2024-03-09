package ei.algobaroapi.domain.room_member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "방 퇴장 시 정보")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomExitResponse {

    @Schema(description = "방 id", example = "30")
    private final Long roomId;

    // 방장 변경 여부
    @Schema(description = "방장 변경 여부", example = "true")
    private final boolean isHostChanged;

    @Schema(description = "변경 된 방장의 멤버 id", example = "2")
    private final Long newHostId;

    public static RoomExitResponse changedHost(Long roomId, Long newHostId) {
        return new RoomExitResponse(roomId, true, newHostId);
    }

    public static RoomExitResponse notChangedHost(Long roomId) {
        return new RoomExitResponse(roomId, false, null);
    }
}
