package ei.algobaroapi.domain.room_member.dto.response;

import ei.algobaroapi.domain.room_member.domain.RoomMember;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "방 방장 정보")
public class RoomHostManualResponseDto {

    @Schema(description = "방 번호", example = "30")
    private String roomShortUuid;

    @Schema(description = "이전 방장의 방-멤버 번호", example = "1")
    private Long previousHostId;

    @Schema(description = "이전 방장의 아이디", example = "test1")
    private String previousHostNickname;

    @Schema(description = "현재 방장의 방-멤버 번호", example = "2")
    private Long newHostId;

    @Schema(description = "현재 방장의 닉네임", example = "test2")
    private String newHostNickname;

    public static RoomHostManualResponseDto of(
            String roomShortUuid,
            RoomMember previousHost,
            RoomMember newHost
    ) {
        return RoomHostManualResponseDto.builder()
                .roomShortUuid(roomShortUuid)
                .previousHostId(previousHost.getId())
                .previousHostNickname(previousHost.getMember().getNickname())
                .newHostId(newHost.getId())
                .newHostNickname(newHost.getMember().getNickname())
                .build();
    }
}
