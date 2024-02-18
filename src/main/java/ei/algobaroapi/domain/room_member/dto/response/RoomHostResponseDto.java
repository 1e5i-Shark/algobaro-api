package ei.algobaroapi.domain.room_member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "방 방장 정보")
public class RoomHostResponseDto {

    @Schema(description = "방-멤버 번호", example = "3")
    private Long id;

    @Schema(description = "방 번호", example = "30")
    private Long roomId;
}
