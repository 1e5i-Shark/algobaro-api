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

    @Schema(description = "컴파일 성공 여부", example = "true")
    private final boolean isCompileSuccess;

    public static CompileExecutionResponse success(String result) {
        return new CompileExecutionResponse(result, true);
    }

    public static CompileExecutionResponse failure() {
        return new CompileExecutionResponse("Compile Fail", false);
    }
}
