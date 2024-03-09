package ei.algobaroapi.domain.member.service;

import ei.algobaroapi.domain.auth.util.PasswordUtil;
import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.domain.MemberRepository;
import ei.algobaroapi.domain.member.domain.vo.EmailVo;
import ei.algobaroapi.domain.member.dto.request.MemberGeneralUpdateRequest;
import ei.algobaroapi.domain.member.dto.request.MemberPasswordUpdateRequest;
import ei.algobaroapi.domain.member.dto.response.MemberDetailResponse;
import ei.algobaroapi.domain.member.exception.MemberFoundException;
import ei.algobaroapi.domain.member.exception.common.MemberErrorCode;
import ei.algobaroapi.global.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final PasswordUtil passwordUtil;
    private final MemberRepository memberRepository;
    private final S3Util s3Util;

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
        return memberRepository.findById(id)
                .orElseThrow(() -> MemberFoundException.of(MemberErrorCode.EMAIL_NOT_FOUND));
    }

    @Override
    public MemberDetailResponse getMemberDetailById(Long id) {
        return MemberDetailResponse.of(this.getMemberById(id));
    }

    @Override
    @Transactional
    public void updateMemberProfileImageInfo(Long id, MultipartFile multipartFile) {
        Member findMember = this.getMemberById(id);

        String profileImageUrl = s3Util.upload(multipartFile);

        findMember.updateProfileImage(profileImageUrl);
    }

    @Override
    @Transactional
    public void updateMemberGeneralInfo(Long id, MemberGeneralUpdateRequest request) {
        Member findMember = this.getMemberById(id);

        findMember.updateGeneralInfo(request);
    }

    @Override
    @Transactional
    public void updateMemberPassword(Long id, MemberPasswordUpdateRequest request) {
        Member findMember = this.getMemberById(id);

        if (!passwordUtil.isPasswordMatch(request.getCurrentPassword(), findMember.getPassword())) {
            throw MemberFoundException.of(MemberErrorCode.PASSWORD_NOT_MATCH);
        }

        findMember.updatePassword(request);
    }

    @Override
    public boolean isExistingMemberByEmail(String email) {
        return memberRepository.existsByEmail(new EmailVo(email));
    }

    @Override
    public boolean isExistingMemberByNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }
}
