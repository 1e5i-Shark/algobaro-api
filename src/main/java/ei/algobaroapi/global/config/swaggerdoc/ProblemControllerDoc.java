package ei.algobaroapi.global.config.swaggerdoc;

import ei.algobaroapi.domain.problem.dto.request.ProblemFindRequest;
import ei.algobaroapi.domain.problem.dto.response.ProblemHtmlResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Problem", description = "문제 관련 API")
@SuppressWarnings("unused")
public interface ProblemControllerDoc {

    @Operation(summary = "문제 정보 조회", description = "문제 사이트의 내용을 HTML로 가져옵니다.")
    ProblemHtmlResponse getProblemHtml(ProblemFindRequest request);
}
