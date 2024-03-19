package ei.algobaroapi.domain.solve.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "코드 제출 응답 결과")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeSubmissionResponse {

    @Schema(description = "제출 코드", example = "import java.io.BufferedReader;\nimport java.io.IOException;\nimport java.io.InputStreamReader;\n\npublic class Main {\n    public static void main(String[] args) throws IOException {\n        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));\n        String[] input = br.readLine().split(\" \");\n        int a = Integer.parseInt(input[0]);\n        int b = Integer.parseInt(input[1]);\n        System.out.println(a + b);\n    }\n}")
    private final String submissionCode;

    public static CodeSubmissionResponse of(String submissionCode) {
        return new CodeSubmissionResponse(submissionCode);
    }
}
