package ei.algobaroapi.domain.problem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "문제 풀이 여부 조회 요청 정보")
@Getter
@Builder
public class ProblemSolveRequest {

    @Schema(description = "문제 링크", example = "https://www.acmicpc.net/problem/1000")
    private String problemLink;

    @Schema(description = "사용자 BOJ 아이디", example = "user_boj")
    private String userBojId;
}
