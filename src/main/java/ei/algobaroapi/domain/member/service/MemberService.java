package ei.algobaroapi.domain.member.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.dto.request.MemberProfileImageUpdateRequest;
import ei.algobaroapi.domain.member.dto.request.MemberGeneralUpdateRequest;
import ei.algobaroapi.domain.member.dto.request.MemberPasswordUpdateRequest;
import ei.algobaroapi.domain.member.dto.response.MemberDetailResponse;

public interface MemberService {

    Member getMemberByEmail(String email);

    Member addMember(Member member);

    Member getMemberById(Long id);

    MemberDetailResponse getMemberDetailById(Long id);

    void updateMemberProfileImageInfo(Long id, MemberProfileImageUpdateRequest request);

    void updateMemberGeneralInfo(Long id, MemberGeneralUpdateRequest request);

    void updateMemberPassword(Long id, MemberPasswordUpdateRequest request);

    boolean isExistingMemberByEmail(String email);

    boolean isExistingMemberByNickname(String nickname);
}
