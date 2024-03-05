package ei.algobaroapi.domain.room_member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HostChangeRequestDto {

    @Schema(description = "방 번호", example = "4")
    private Long roomId;

    @Schema(description = "현재 방장 번호", example = "1")
    private Long hostId;

    @Schema(description = "현재 참여자 번호", example = "2")
    private Long organizerId;
}
