package ei.algobaroapi.domain.member.controller;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.dto.request.MemberGeneralUpdateRequest;
import ei.algobaroapi.domain.member.dto.request.MemberPasswordUpdateRequest;
import ei.algobaroapi.domain.member.dto.response.MemberDetailResponse;
import ei.algobaroapi.domain.member.service.MemberService;
import ei.algobaroapi.global.config.swaggerdoc.MemberControllerDoc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController implements MemberControllerDoc {

    private final MemberService memberService;

    @Override
    @GetMapping("/members/my")
    @PreAuthorize("hasRole('ROLE_USER')")
    public MemberDetailResponse getMyInfo(@AuthenticationPrincipal Member member) {
        return memberService.getMemberDetailById(member.getId());
    }

    @Override
    @PatchMapping("/members/my/profile-image")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void updateMemberProfileImageInfo(
            @AuthenticationPrincipal Member member,
            @RequestPart("image") MultipartFile multipartFile
    ) {
        memberService.updateMemberProfileImageInfo(member.getId(), multipartFile);
    }

    @Override
    @PatchMapping("/members/my/general")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void updateMemberGeneralInfo(
            @AuthenticationPrincipal Member member,
            @RequestBody @Valid MemberGeneralUpdateRequest memberGeneralUpdateRequest
    ) {
        memberService.updateMemberGeneralInfo(member.getId(), memberGeneralUpdateRequest);
    }

    @Override
    @PatchMapping("/members/my/password")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void updateMemberPassword(
            @AuthenticationPrincipal Member member,
            @RequestBody @Valid MemberPasswordUpdateRequest memberPasswordUpdateRequest
    ) {
        memberService.updateMemberPassword(member.getId(), memberPasswordUpdateRequest);
    }

    @Override
    @DeleteMapping("/members/my/profile-image")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteMemberProfileImage(@AuthenticationPrincipal Member member,
            @RequestParam String imageAddress) {
        memberService.deleteMemberProfileImage(member.getId(), imageAddress);
    }
}
