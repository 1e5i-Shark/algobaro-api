package ei.algobaroapi.domain.member.controller;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.dto.MemberSignUpRequest;
import ei.algobaroapi.domain.member.dto.MemberSignInRequest;
import ei.algobaroapi.domain.member.service.MemberService;
import ei.algobaroapi.global.config.swaggerdoc.MemberControllerDoc;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController implements MemberControllerDoc {

    private final MemberService memberService;

    @Override
    @PostMapping("/members/sign-up")
    public void signUp(@RequestBody MemberSignUpRequest request) {
        this.memberService.signUp(request);
    }

    @Override
    @PostMapping("/members/sign-in")
    public String signIn(@RequestBody MemberSignInRequest request) {
        return this.memberService.signIn(request);
    }

    @Override
    @GetMapping("/members/test")
    public String hello(@AuthenticationPrincipal Member member) {
        System.out.println(member.getEmail().getEmail());
        return "Hello, World!";
    }
}
