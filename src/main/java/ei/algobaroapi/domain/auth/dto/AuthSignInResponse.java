package ei.algobaroapi.domain.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthSignInResponse {

    private final String accessToken;

    public static AuthSignInResponse of(String accessToken) {
        return new AuthSignInResponse(accessToken);
    }
}
