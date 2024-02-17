package ei.algobaroapi.domain.problem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "테스트 케이스 정보")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProblemTestCaseResponse {

    @Schema(description = "테스트 케이스 번호", example = "1")
    private final int caseNumber;

    @Schema(description = "테스트 케이스 입력 값", example = "1 2")
    private final String input;

    @Schema(description = "테스트 케이스 출력 값", example = "3")
    private final String output;

    public static ProblemTestCaseResponse of(int caseNumber, String input, String output) {
        return new ProblemTestCaseResponse(caseNumber, input, output);
    }
}
