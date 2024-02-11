package ei.algobaroapi.domain.member.controller;

import ei.algobaroapi.domain.member.dto.MemberSignUpRequest;
import ei.algobaroapi.domain.member.dto.MemberSignInRequest;
import ei.algobaroapi.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members/sign-up")
    public void signUp(@RequestBody MemberSignUpRequest request) {
        this.memberService.signUp(request);
    }

    @PostMapping("/members/sign-in")
    public String signIn(@RequestBody MemberSignInRequest request) {
        return this.memberService.signIn(request);
    }
}
