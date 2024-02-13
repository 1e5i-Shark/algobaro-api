package ei.algobaroapi.domain.member.controller;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.dto.MemberSignUpRequest;
import ei.algobaroapi.domain.member.dto.MemberSignInRequest;
import ei.algobaroapi.domain.member.service.AuthService;
import ei.algobaroapi.global.config.swaggerdoc.AuthControllerDoc;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController implements AuthControllerDoc {

    private final AuthService authService;

    @Override
    @PostMapping("/auth/sign-up")
    public void signUp(@RequestBody MemberSignUpRequest request) {
        this.authService.signUp(request);
    }

    @Override
    @PostMapping("/auth/sign-in")
    public String signIn(@RequestBody MemberSignInRequest request) {
        return this.authService.signIn(request);
    }

    @Override
    @GetMapping("/auth/test")
    public String hello(@AuthenticationPrincipal Member member) {
        System.out.println(member.getEmail().getEmail());
        return "Hello, World!";
    }
}