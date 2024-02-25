package ei.algobaroapi.domain.solve.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "테스트 케이스 결과")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BojTestCaseResult {

    @Schema(description = "테스트 케이스 번호", example = "1")
    private final int caseNumber;

    @Schema(description = "테스트 케이스 입력 값", example = "1 2")
    private final String input;

    @Schema(description = "테스트 케이스 출력 값", example = "3")
    private final String output;

    @Schema(description = "테스트 케이스 실행 결과", example = "3")
    private final String result;

    @Schema(description = "테스트 케이스 결과", example = "true")
    private final boolean success;

    @Builder
    public BojTestCaseResult(int caseNumber, String input, String output, String result) {
        this.caseNumber = caseNumber;
        this.input = input;
        this.output = output;
        this.result = result;
        this.success = compareOutputAndResult(output, result);
    }

    private static boolean compareOutputAndResult(String output, String result) {
        return output.trim().equals(result.trim());
    }
}
