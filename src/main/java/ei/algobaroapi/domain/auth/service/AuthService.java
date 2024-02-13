package ei.algobaroapi.domain.auth.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.domain.MemberRepository;
import ei.algobaroapi.domain.member.domain.vo.EmailVo;
import ei.algobaroapi.domain.auth.dto.MemberSignUpRequest;
import ei.algobaroapi.domain.auth.dto.MemberSignInRequest;
import ei.algobaroapi.domain.auth.exception.AuthErrorCode;
import ei.algobaroapi.domain.auth.exception.umm.MemberFoundException;
import ei.algobaroapi.domain.auth.exception.umm.MemberPasswordException;
import ei.algobaroapi.domain.auth.util.PasswordUtil;
import ei.algobaroapi.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final PasswordUtil passwordUtil;

    @Transactional
    public void signUp(MemberSignUpRequest request) {
        String encryptPassword = passwordUtil.validateAndEncryptPassword(request.getPassword());
        memberRepository.save(request.toEntity(encryptPassword));
    }

    @Transactional
    public String signIn(MemberSignInRequest request) {
        Member member = memberRepository.findByEmailAndDeletedAtIsNull(new EmailVo(request.getEmail()))
                .orElseThrow(() -> MemberFoundException.of(AuthErrorCode.EMAIL_NOT_FOUND));
        if (!passwordUtil.isPasswordMatch(request.getPassword(), member.getPassword())) {
            throw MemberPasswordException.of(AuthErrorCode.PASSWORD_NOT_MATCH);
        }

        return jwtProvider.generateToken(member.getUsername(), member.getRoles());
    }
}
