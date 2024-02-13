package ei.algobaroapi.domain.auth.service;

import ei.algobaroapi.domain.auth.dto.MemberSignInRequest;
import ei.algobaroapi.domain.auth.dto.MemberSignInResponse;
import ei.algobaroapi.domain.auth.dto.MemberSignUpRequest;
import ei.algobaroapi.domain.auth.exception.AuthErrorCode;
import ei.algobaroapi.domain.auth.exception.umm.AuthPasswordException;
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
    public void signUp(MemberSignUpRequest request) {
        String encryptPassword = passwordUtil.validateAndEncryptPassword(request.getPassword());
        memberService.addMember(request.toEntity(encryptPassword));
    }

    @Transactional
    public MemberSignInResponse signIn(MemberSignInRequest request) {
        Member member = memberService.getMemberByEmail(request.getEmail());
        if (!passwordUtil.isPasswordMatch(request.getPassword(), member.getPassword())) {
            throw AuthPasswordException.of(AuthErrorCode.PASSWORD_NOT_MATCH);
        }

        String accessToken = jwtProvider.generateToken(member.getUsername(), member.getRoles());

        return MemberSignInResponse.of(accessToken);
    }
}
