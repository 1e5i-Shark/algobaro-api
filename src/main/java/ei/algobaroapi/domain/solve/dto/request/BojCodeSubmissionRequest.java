package ei.algobaroapi.domain.solve.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "코드 제출 요청")
@Getter
@Builder
public class BojCodeSubmissionRequest {

    @Schema(description = "방 식별 값", example = "123e4567-e89b-12d3-a456-426614174000")
    private String roomUuid;

    @Schema(description = "코드 실행 언어", example = "java")
    private String language;

    @Schema(description = "코드 실행 코드", example = "import java.io.BufferedReader;\nimport java.io.InputStreamReader;\n\npublic class Main {\n    public static void main(String[] args) {\n        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));\n        String[] input = br.readLine().split(\" \");\n        int a = Integer.parseInt(input[0]);\n        int b = Integer.parseInt(input[1]);\n        System.out.println(a + b);\n    }\n}")
    private String code;

    @Schema(description = "문제 링크", example = "https://www.acmicpc.net/problem/1000")
    private String problemLink;
}
