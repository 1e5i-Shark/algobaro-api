package ei.algobaroapi.domain.auth.dto;

import lombok.Getter;

@Getter
public class AuthSignInRequest {

    private String email;
    private String password;
}
