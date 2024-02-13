package ei.algobaroapi.domain.auth.dto;

import lombok.Getter;

@Getter
public class MemberSignInRequest {

    private String email;
    private String password;
}
