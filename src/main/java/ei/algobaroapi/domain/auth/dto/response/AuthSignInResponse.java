package ei.algobaroapi.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthSignInResponse {

    @Schema(description = "엑세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0ZXN0QGVtYWlsLmNvbSIsImlhdCI6MTYyMzIwNzIwMH0.3Jk")
    private final String accessToken;

    public static AuthSignInResponse of(String accessToken) {
        return new AuthSignInResponse(accessToken);
    }
}
