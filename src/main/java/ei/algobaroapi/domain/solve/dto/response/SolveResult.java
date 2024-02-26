package ei.algobaroapi.domain.solve.dto.response;

import ei.algobaroapi.domain.solve.domain.SolveStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "풀이 결과")
@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SolveResult {

    @Schema(description = "멤버 id", example = "1")
    private final Long memberId;

    @Schema(description = "풀이 언어", example = "java")
    private final String language;

    @Schema(description = "풀이 코드", example = "import java.io.BufferedReader;\nimport java.io.IOException;\nimport java.io.InputStreamReader;\n\npublic class Main {\n    public static void main(String[] args) throws IOException {\n        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));\n        String[] input = br.readLine().split(\" \");\n        int a = Integer.parseInt(input[0]);\n        int b = Integer.parseInt(input[1]);\n        System.out.println(a + b);\n    }\n}")
    private final String code;

    @Schema(description = "풀이 결과", example = "SUCCESS")
    private final SolveStatus solveStatus;
}
