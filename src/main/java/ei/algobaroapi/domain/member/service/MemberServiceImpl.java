package ei.algobaroapi.domain.member.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.domain.MemberRepository;
import ei.algobaroapi.domain.member.domain.vo.EmailVo;
import ei.algobaroapi.domain.member.dto.response.MemberDetailResponse;
import ei.algobaroapi.domain.member.dto.request.MemberDetailUpdateRequest;
import ei.algobaroapi.domain.member.exception.MemberFoundException;
import ei.algobaroapi.domain.member.exception.common.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmailAndDeletedAtIsNull(new EmailVo(email))
                .orElseThrow(() -> MemberFoundException.of(MemberErrorCode.EMAIL_NOT_FOUND));
    }

    @Override
    @Transactional
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member getMemberById(Long id) {
        return null;
    }

    @Override
    public MemberDetailResponse getMemberDetailById(Long id) {
        return null;
    }

    @Override
    public void updateMemberDetail(Long id, MemberDetailUpdateRequest request) {
    }
}
