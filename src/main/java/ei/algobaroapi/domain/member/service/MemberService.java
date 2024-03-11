package ei.algobaroapi.domain.member.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.dto.request.MemberGeneralUpdateRequest;
import ei.algobaroapi.domain.member.dto.request.MemberPasswordUpdateRequest;
import ei.algobaroapi.domain.member.dto.response.MemberDetailResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {

    Member getMemberByEmail(String email);

    Member addMember(Member member);

    Member getMemberById(Long id);

    MemberDetailResponse getMemberDetailById(Long id);

    void updateMemberProfileImageInfo(Long id, MultipartFile multipartFile);

    void updateMemberGeneralInfo(Long id, MemberGeneralUpdateRequest request);

    void updateMemberPassword(Long id, MemberPasswordUpdateRequest request);

    void deleteMemberProfileImage(Long id, String imageAddress);

    boolean isExistingMemberByEmail(String email);

    boolean isExistingMemberByNickname(String nickname);
}
