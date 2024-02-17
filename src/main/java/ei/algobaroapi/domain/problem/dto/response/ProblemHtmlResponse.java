package ei.algobaroapi.domain.problem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "문제 정보")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProblemHtmlResponse {

    @Schema(description = "문제 정보 html", example = "<h1>문제 정보</h1>")
    private final String problemInfoHtml;

    public static ProblemHtmlResponse of(String problemInfoHtml) {
        return new ProblemHtmlResponse(problemInfoHtml);
    }
}
