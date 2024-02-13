package ei.algobaroapi.domain.auth.service;

import ei.algobaroapi.domain.auth.dto.AuthSignInRequest;
import ei.algobaroapi.domain.auth.dto.AuthSignInResponse;
import ei.algobaroapi.domain.auth.dto.AuthSignUpRequest;
import ei.algobaroapi.domain.auth.exception.common.AuthErrorCode;
import ei.algobaroapi.domain.auth.exception.AuthPasswordException;
import ei.algobaroapi.domain.auth.util.PasswordUtil;
import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.service.MemberService;
import ei.algobaroapi.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;
    private final PasswordUtil passwordUtil;

    @Transactional
    public void signUp(AuthSignUpRequest request) {
        String encryptPassword = passwordUtil.validateAndEncryptPassword(request.getPassword());
        memberService.addMember(request.toEntity(encryptPassword));
    }

    @Transactional
    public AuthSignInResponse signIn(AuthSignInRequest request) {
        Member member = memberService.getMemberByEmail(request.getEmail());
        if (!passwordUtil.isPasswordMatch(request.getPassword(), member.getPassword())) {
            throw AuthPasswordException.of(AuthErrorCode.PASSWORD_NOT_MATCH);
        }

        String accessToken = jwtProvider.generateToken(member.getUsername(), member.getRoles());

        return AuthSignInResponse.of(accessToken);
    }
}
