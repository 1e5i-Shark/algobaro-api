package ei.algobaroapi.global.config.swaggerdoc;

import ei.algobaroapi.domain.auth.dto.request.AuthSignInRequest;
import ei.algobaroapi.domain.auth.dto.request.AuthSignUpRequest;
import ei.algobaroapi.domain.auth.dto.response.AuthSignInResponse;
import ei.algobaroapi.domain.auth.dto.response.AuthSignUpResponse;
import ei.algobaroapi.domain.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;

@Tag(name = "Auth", description = "회원 인증 도메인 API")
@SuppressWarnings("unused")
public interface AuthControllerDoc {

    @Operation(summary = "회원 가입", description = "회원가입을 합니다.")
    @ApiResponse(responseCode = "201", description = "회원가입 성공")
    @ApiResponse(responseCode = "E00101", description = "이미 존재하는 이메일으로 회원가입 시도", content = @Content)
    @ApiResponse(responseCode = "E00102", description = "이미 존재하는 닉네임으로 회원가입 시도", content = @Content)
    AuthSignUpResponse signUp(AuthSignUpRequest request);

    @Operation(summary = "로그인", description = "이메일, 비밀번호로 로그인을 합니다.")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    @ApiResponse(responseCode = "E01301", description = "가입되지 않은 이메일입니다.", content = @Content)
    @ApiResponse(responseCode = "E00202", description = "비밀번호가 일치하지 않습니다.", content = @Content)
    AuthSignInResponse signIn(AuthSignInRequest request);

    @Operation(summary = "로그인 회원 검증 테스트", description = "로그인된 회원의 이메일을 반환합니다.")
    Map<String, String> hello(Member member);
}
