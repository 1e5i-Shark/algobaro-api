package ei.algobaroapi.domain.member.domain.vo;

import ei.algobaroapi.domain.member.exception.MemberInputException;
import ei.algobaroapi.domain.member.exception.common.MemberErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class EmailVo {

    private static final String EMAIL_REGEX = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-z]+[.]+[a-z]{2,3}$";

    @Column(name = "email", nullable = false)
    private String email;

    public EmailVo(String email) {
        if (!isValidEmail(email)) {
            throw MemberInputException.of(MemberErrorCode.INVALID_EMAIL);
        }
        this.email = email;
    }

    private boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }
}
