package ei.algobaroapi.domain.solve.dto.response;

import ei.algobaroapi.domain.solve.domain.SolveHistory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "풀이 히스토리 조회 응답")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SolveHistoryResponse {

    @Schema(description = "풀이 히스토리 식별 id", example = "1")
    private final Long id;

    @Schema(description = "방 식별 값", example = "123e4567-e89b-12d3-a456-426614174000")
    private final String roomUuid;

    @Schema(description = "풀이 언어", example = "java")
    private final String language;

    @Schema(description = "풀이 결과", example = "SUCCESS")
    private final String solveStatus;

    @Schema(description = "풀이 시간", example = "2024-01-01T00:00:00")
    private final String solvedAt;

    public static SolveHistoryResponse of(SolveHistory solveHistory) {
        return new SolveHistoryResponse(
                solveHistory.getId(),
                solveHistory.getRoomUuid(),
                solveHistory.getCodeLanguage(),
                solveHistory.getSolveStatus().name(),
                solveHistory.getEndAt().toString()
        );
    }
}
