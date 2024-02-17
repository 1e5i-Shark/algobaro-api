package ei.algobaroapi.domain.solve.dto.request;

import ei.algobaroapi.domain.solve.domain.SolveStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort.Direction;

@Schema(description = "풀이 히스토리 조회 요청 정보")
@Getter
@Builder
public class SolveHistoryListFindRequest {

    @Schema(description = "페이지 번호", example = "1")
    private Integer page;

    @Schema(description = "페이지 크기", example = "10")
    private Integer size;

    @Schema(description = "풀이 상태", example = "SUCCESS")
    private SolveStatus status;

    @Schema(description = "검색 키워드", example = "키워드")
    private String searchKeyword;

    @Schema(description = "정렬 필드", example = "id")
    private SortField sortField;

    @Schema(description = "정렬 방향", example = "DESC")
    private Direction sortDirection;

    @Getter
    @RequiredArgsConstructor
    public enum SortField {
        ID("id");

        private final String value;
    }
}
