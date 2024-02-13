package ei.algobaroapi.global.config.swaggerdoc;

import ei.algobaroapi.domain.auth.dto.AuthSignInResponse;
import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.auth.dto.AuthSignInRequest;
import ei.algobaroapi.domain.auth.dto.AuthSignUpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth", description = "회원 인증 도메인 API")
@SuppressWarnings("unused")
public interface AuthControllerDoc {

    @Operation(summary = "회원 가입", description = "회원가입을 합니다.")
    void signUp(@RequestBody AuthSignUpRequest request);

    @Operation(summary = "로그인", description = "이메일, 비밀번호로 로그인을 합니다.")
    AuthSignInResponse signIn(@RequestBody AuthSignInRequest request);

    @Operation(summary = "테스트", description = "테스트용 API")
    String hello(@AuthenticationPrincipal Member member);
}
