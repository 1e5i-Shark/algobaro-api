package ei.algobaroapi.domain.room_member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Schema(description = "방장 자동 변경 요청 정보")
@NoArgsConstructor
@AllArgsConstructor
public class HostAutoChangeRequestDto {

    @Schema(description = "방 번호", example = "4")
    private Long roomId;
}
