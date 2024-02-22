package ei.algobaroapi.domain.auth.service;

import ei.algobaroapi.domain.auth.dto.request.AuthSignInRequest;
import ei.algobaroapi.domain.auth.dto.request.AuthSignUpRequest;
import ei.algobaroapi.domain.auth.dto.response.AuthSignInResponse;
import ei.algobaroapi.domain.auth.dto.response.AuthSignUpResponse;
import ei.algobaroapi.domain.auth.exception.AuthEmailExistenceException;
import ei.algobaroapi.domain.auth.exception.AuthPasswordException;
import ei.algobaroapi.domain.auth.exception.common.AuthErrorCode;
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
    public AuthSignUpResponse signUp(AuthSignUpRequest request) {
        checkMemberIsExisting(request.getEmail()); // 해당 이메일로 가입한 회원이 있는지 확인
        checkNicknameIsExisting(request.getNickname()); // 이미 존재하는 닉네임인지 확인

        String encryptPassword = passwordUtil.validateAndEncryptPassword(request.getPassword());

        return AuthSignUpResponse.of(
                memberService.addMember(request.toEntity(encryptPassword)).getId());
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

    private void checkMemberIsExisting(String email) {
        if (memberService.getMemberByEmail(email) != null) {
            throw AuthEmailExistenceException.of(AuthErrorCode.EXISTING_EMAIL);
        }
    }

    private void checkNicknameIsExisting(String nickname) {
    }
}
