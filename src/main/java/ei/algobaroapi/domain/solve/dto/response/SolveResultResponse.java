package ei.algobaroapi.domain.solve.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "풀이 결과 조회")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SolveResultResponse {


    @Schema(description = "풀이 결과 리스트")
    private final List<SolveResult> solveResults;

    public static SolveResultResponse of(List<SolveResult> solveResults) {
        return new SolveResultResponse(solveResults);
    }
}
