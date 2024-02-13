package ei.algobaroapi.domain.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberSignInResponse {

    private final String accessToken;

    public static MemberSignInResponse of(String accessToken) {
        return new MemberSignInResponse(accessToken);
    }
}
