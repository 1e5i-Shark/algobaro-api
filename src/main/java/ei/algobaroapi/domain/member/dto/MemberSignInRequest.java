package ei.algobaroapi.domain.member.dto;

import lombok.Getter;

@Getter
public class MemberSignInRequest {

    private String email;
    private String password;
}
