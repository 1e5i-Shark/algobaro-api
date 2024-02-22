package ei.algobaroapi.global.config.swaggerdoc;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.solve.dto.request.BojCodeSubmissionRequest;
import ei.algobaroapi.domain.solve.dto.request.SolveHistoryListFindRequest;
import ei.algobaroapi.domain.solve.dto.response.BojCodeSubmissionResponse;
import ei.algobaroapi.domain.solve.dto.response.SolveHistoryDetailResponse;
import ei.algobaroapi.domain.solve.dto.response.SolveHistoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Solve", description = "문제 풀이 관련 API")
@SuppressWarnings("unused")
public interface SolveControllerDoc {

    @Operation(summary = "코드 제출 - 작업 중", description = "문제 코드를 제출합니다.")
    @ApiResponse(responseCode = "201", description = "코드 제출 성공")
    BojCodeSubmissionResponse submissionCode(Member member, BojCodeSubmissionRequest request);

    @Operation(summary = "문제 풀이 히스토리 리스트 조회", description = "문제 풀이 히스토리 리스트를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "문제 풀이 히스토리 리스트 조회 성공")
    List<SolveHistoryResponse> getHistoryList(Member member, SolveHistoryListFindRequest request);

    @Operation(summary = "문제 풀이 히스토리 상세 조회", description = "문제 풀이 히스토리를 상세 조회합니다.")
    @ApiResponse(responseCode = "200", description = "문제 풀이 히스토리 상세 조회 성공")
    @ApiResponse(responseCode = "E02301", description = "존재하지 않는 풀이 내역입니다.", content = @Content)
    @ApiResponse(responseCode = "E02201", description = "해당 풀이 내역에 접근할 수 없습니다.", content = @Content)
    SolveHistoryDetailResponse getHistory(Member member, Long solveId);
}
