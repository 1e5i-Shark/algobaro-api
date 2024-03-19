package ei.algobaroapi.domain.solve.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "코드 제출 요청")
@Getter
@Builder
public class CodeSubmissionRequest {

    @Schema(description = "방 식별 값", example = "123e4567")
    private String roomShortUuid;

    @Schema(description = "코드 실행 언어\n\npython - python3\n\njavascript - nodejs\n\njava - java\n\nc++ - cpp", example = "java")
    private String language;

    @Schema(description = "코드 실행 코드", example = "import java.io.BufferedReader;\nimport java.io.IOException;\nimport java.io.InputStreamReader;\n\npublic class Main {\n    public static void main(String[] args) throws IOException {\n        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));\n        String[] input = br.readLine().split(\" \");\n        int a = Integer.parseInt(input[0]);\n        int b = Integer.parseInt(input[1]);\n        System.out.println(a + b);\n    }\n}")
    private String code;

    @Schema(description = "코드 실행 결과", example = "SUCCESS / FAIL")
    private String solveStatus;
}
