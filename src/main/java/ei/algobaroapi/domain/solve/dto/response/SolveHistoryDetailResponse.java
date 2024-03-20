package ei.algobaroapi.domain.solve.dto.response;

import ei.algobaroapi.domain.solve.domain.SolveHistory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "풀이 히스토리 상세 조회 응답")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SolveHistoryDetailResponse {

    @Schema(description = "풀이 히스토리 식별 id", example = "1")
    private final Long id;

    @Schema(description = "방 식별 값", example = "123e4567-e89b-12d3-a456-426614174000")
    private final String roomUuid;

    @Schema(description = "풀이 언어", example = "java")
    private final String language;

    @Schema(description = "풀이 코드", example = "import java.io.BufferedReader;\nimport java.io.IOException;\nimport java.io.InputStreamReader;\n\npublic class Main {\n    public static void main(String[] args) throws IOException {\n        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));\n        String[] input = br.readLine().split(\" \");\n        int a = Integer.parseInt(input[0]);\n        int b = Integer.parseInt(input[1]);\n        System.out.println(a + b);\n    }\n}")
    private final String code;

    @Schema(description = "풀이 결과", example = "SUCCESS")
    private final String solveStatus;

    @Schema(description = "실패 이유", example = "MEMORY_LIMIT / TIME_LIMIT / ETC / NULL")
    private final String failureReason;

    @Schema(description = "풀이 시간", example = "2024-01-01T00:00:00")
    private final String solvedAt;

    @Schema(description = "문제 플랫폼", example = "BOJ")
    private final String platform;

    @Schema(description = "문제 링크", example = "https://www.acmicpc.net/problem/1000")
    private final String problemLink;

    public static SolveHistoryDetailResponse of(SolveHistory solveHistory) {
        return new SolveHistoryDetailResponse(
                solveHistory.getId(),
                solveHistory.getRoomUuid(),
                solveHistory.getCodeLanguage(),
                solveHistory.getInputCode(),
                solveHistory.getSolveStatus().name(),
                solveHistory.getFailureReason(),
                solveHistory.getStartAt().toString(),
                solveHistory.getProblemPlatform().name(),
                solveHistory.getProblemLink()
        );
    }
}
