package ei.algobaroapi.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Schema(description = "로그인 요청 정보")
public class AuthSignInRequest {

    @NotNull
    @Schema(description = "이메일", example = "test@test.com")
    private String email;
    @NotNull
    @Schema(description = "비밀번호", example = "password1234!")
    private String password;
}
