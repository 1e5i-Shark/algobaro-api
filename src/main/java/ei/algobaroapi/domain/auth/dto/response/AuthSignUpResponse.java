package ei.algobaroapi.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthSignUpResponse {

    @Schema(description = "생성된 회원 id", example = "3")
    private final Long id;

    public static AuthSignUpResponse of(Long id) {
        return new AuthSignUpResponse(id);
    }
}
