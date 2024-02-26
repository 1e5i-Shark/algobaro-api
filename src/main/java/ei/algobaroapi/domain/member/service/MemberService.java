package ei.algobaroapi.domain.member.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.dto.request.MemberDetailUpdateRequest;
import ei.algobaroapi.domain.member.dto.response.MemberDetailResponse;

public interface MemberService {

    Member getMemberByEmail(String email);

    Member addMember(Member member);

    Member getMemberById(Long id);

    MemberDetailResponse getMemberDetailById(Long id);

    void updateMemberDetail(Long id, MemberDetailUpdateRequest request);

    boolean isExistingMemberByEmail(String email);

    boolean isExistingMemberByNickname(String nickname);
}
