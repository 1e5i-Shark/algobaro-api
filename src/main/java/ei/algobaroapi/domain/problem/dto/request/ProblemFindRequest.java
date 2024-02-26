package ei.algobaroapi.domain.problem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "문제 조회 요청 정보")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProblemFindRequest {

    @Schema(description = "문제 링크", example = "https://www.acmicpc.net/problem/1000")
    private String problemLink;
}
