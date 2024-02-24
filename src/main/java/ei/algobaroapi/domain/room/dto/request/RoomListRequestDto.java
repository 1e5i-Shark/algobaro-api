package ei.algobaroapi.domain.room.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "방 리스트 조회 요청 정보")
@Getter
@Builder
public class RoomListRequestDto {

    @Schema(description = "페이지 번호", example = "0")
    private Integer page;

    @Schema(description = "페이지 크기", example = "6")
    private Integer size;
}
