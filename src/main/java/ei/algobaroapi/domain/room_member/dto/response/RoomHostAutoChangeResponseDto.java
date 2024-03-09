package ei.algobaroapi.domain.room_member.dto.response;

import ei.algobaroapi.domain.room_member.domain.RoomMember;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "방 방장 자동 변경 정보")
public class RoomHostAutoChangeResponseDto {

    @Schema(description = "방 번호", example = "30")
    private Long roomId;

    @Schema(description = "현재 방장의 멤버 id", example = "2")
    private Long newHostId;

    @Schema(description = "현재 방장의 닉네임", example = "test2")
    private String newHostNickname;

    public static RoomHostAutoChangeResponseDto of(Long roomId, RoomMember newHost) {
        return RoomHostAutoChangeResponseDto.builder()
                .roomId(roomId)
                .newHostId(newHost.getMember().getId())
                .newHostNickname(newHost.getMember().getNickname())
                .build();
    }
}
