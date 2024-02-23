package ei.algobaroapi.domain.solve.dto.request;

import ei.algobaroapi.domain.solve.domain.SolveStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "풀이 히스토리 조회 요청 정보")
@Getter
@Builder
public class SolveHistoryListFindRequest {

    @Schema(description = "페이지 번호", example = "0")
    private Integer page;

    @Schema(description = "페이지 크기", example = "10")
    private Integer size;

    @Schema(description = "풀이 상태", example = "SUCCESS")
    private SolveStatus status;
}
