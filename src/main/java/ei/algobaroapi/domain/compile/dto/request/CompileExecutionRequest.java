package ei.algobaroapi.domain.compile.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "코드 실행 요청 정보")
@Getter
@Builder
public class CompileExecutionRequest {

    @Schema(description = "코드 실행 언어", example = "java")
    private String language;

    @Schema(description = "코드 실행 입력 값", example = "1 2")
    private String input;

    @Schema(description = "코드 실행 코드", example = "import java.io.BufferedReader;\nimport java.io.InputStreamReader;\n\npublic class Main {\n    public static void main(String[] args) {\n        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));\n        String[] input = br.readLine().split(\" \");\n        int a = Integer.parseInt(input[0]);\n        int b = Integer.parseInt(input[1]);\n        System.out.println(a + b);\n    }\n}")
    private String code;
}
