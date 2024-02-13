package ei.algobaroapi.domain.member.service;

import ei.algobaroapi.domain.auth.exception.AuthErrorCode;
import ei.algobaroapi.domain.auth.exception.umm.MemberFoundException;
import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.domain.MemberRepository;
import ei.algobaroapi.domain.member.domain.vo.EmailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmailAndDeletedAtIsNull(new EmailVo(email))
                .orElseThrow(() -> MemberFoundException.of(AuthErrorCode.EMAIL_NOT_FOUND));
    }

    @Transactional
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }
}
