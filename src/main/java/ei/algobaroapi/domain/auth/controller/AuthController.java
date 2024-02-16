package ei.algobaroapi.domain.auth.controller;

import ei.algobaroapi.domain.auth.dto.AuthSignInRequest;
import ei.algobaroapi.domain.auth.dto.AuthSignInResponse;
import ei.algobaroapi.domain.auth.dto.AuthSignUpRequest;
import ei.algobaroapi.domain.auth.service.AuthService;
import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.global.config.swaggerdoc.AuthControllerDoc;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController implements AuthControllerDoc {

    private final AuthService authService;

    @Override
    @PostMapping("/auth/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody AuthSignUpRequest request) {
        this.authService.signUp(request);
    }

    @Override
    @PostMapping("/auth/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public AuthSignInResponse signIn(@RequestBody AuthSignInRequest request) {
        return this.authService.signIn(request);
    }

    @Override
    @GetMapping("/auth/test")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> hello(@AuthenticationPrincipal Member member) {
        return Map.of("email", member.getEmail().getEmail());
    }
}
