package ei.algobaroapi.domain.member.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.domain.MemberRepository;
import ei.algobaroapi.domain.member.domain.vo.EmailVo;
import ei.algobaroapi.domain.member.dto.MemberSignUpRequest;
import ei.algobaroapi.domain.member.dto.MemberSignInRequest;
import ei.algobaroapi.domain.member.util.PasswordUtil;
import ei.algobaroapi.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

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
        Member user = memberRepository.findByEmailAndDeletedAtIsNull(new EmailVo(request.getEmail()))
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));
        if (!passwordUtil.isPasswordMatch(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Password mismatch");
        }

        return jwtProvider.generateToken(user.getUsername(), user.getRoles());
    }
}
