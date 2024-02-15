package ei.algobaroapi.domain.auth.dto;

import ei.algobaroapi.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthSignUpRequest {

    private String email;
    private String password;

    public Member toEntity(String encryptPassword) {
        return Member.builder()
                .email(this.email)
                .password(encryptPassword)
                .build();
    }
}
