package ei.algobaroapi.domain.member.dto;

import ei.algobaroapi.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberSignUpRequest {

    private String email;
    private String password;

    public Member toEntity(String encryptPassword) {
        return Member.builder()
                .email(this.email)
                .password(encryptPassword)
                .build();
    }
}
