package ei.algobaroapi.domain.compile.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "코드 실행 응답 결과")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CompileExecutionResponse {

    @Schema(description = "코드 실행 결과", example = "3")
    private final String result;

    public static CompileExecutionResponse of(String result) {
        return new CompileExecutionResponse(result);
    }
}
