package ei.algobaroapi.domain.solve.dto.response;

import ei.algobaroapi.domain.solve.dto.BojTestCaseResult;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "코드 제출 응답 결과")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BojCodeSubmissionResponse {

    @Schema(description = "테스트 케이스 결과 리스트")
    private final List<BojTestCaseResult> testCaseResults;

    public static BojCodeSubmissionResponse of(List<BojTestCaseResult> testCaseResultList) {
        return new BojCodeSubmissionResponse(testCaseResultList);
    }
}
