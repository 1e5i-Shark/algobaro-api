package ei.algobaroapi.domain.member.controller;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.dto.request.MemberDetailUpdateRequest;
import ei.algobaroapi.domain.member.dto.response.MemberDetailResponse;
import ei.algobaroapi.global.config.swaggerdoc.MemberControllerDoc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController implements MemberControllerDoc {

    @Override
    @GetMapping("/members/my")
    public MemberDetailResponse getMyInfo(@AuthenticationPrincipal Member member) {
        return null;
    }

    @Override
    @PatchMapping("/members/my")
    public void updateMemberInfo(
            @AuthenticationPrincipal Member member,
            @RequestBody @Valid MemberDetailUpdateRequest memberUpdateRequest
    ) {
        //
    }
}
