package ei.algobaroapi.global.config.swaggerdoc;

import ei.algobaroapi.domain.compile.dto.request.CompileExecutionRequest;
import ei.algobaroapi.domain.compile.dto.response.CompileExecutionResponse;
import ei.algobaroapi.domain.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Compile", description = "코드 컴파일 관련 API")
@SuppressWarnings("unused")
public interface CompileControllerDoc {

    @Operation(summary = "코드 제출", description = "사용자가 제출한 코드를 컴파일합니다.")
    @ApiResponse(responseCode = "201", description = "코드 컴파일 성공")
    CompileExecutionResponse compileCode(Member member, CompileExecutionRequest request);
}
