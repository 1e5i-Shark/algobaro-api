package ei.algobaroapi.domain.auth.dto.request;

import ei.algobaroapi.domain.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "회원가입 요청 정보")
public class AuthSignUpRequest {

    @NotNull
    @Schema(description = "이메일", example = "test@test.com")
    private String email;

    @NotNull
    @Schema(description = "닉네임", example = "닉네임뭐로하지")
    private String nickname;

    @NotNull
    @Schema(description = "백준 id", example = "gupeng_0428")
    private String bojId;

    @NotNull
    @Schema(description = "비밀번호", example = "password1234!")
    private String password;

    @NotNull
    @Schema(description = "비밀번호 확인", example = "password1234!")
    private String passwordConfirmation;

    public Member toEntity(String encryptPassword) {
        return Member.builder()
                .email(this.email)
                .password(encryptPassword)
                .nickname(this.nickname)
                .bojId(this.bojId)
                .build();
    }
}
