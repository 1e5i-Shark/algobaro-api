package ei.algobaroapi.domain.compile.controller;

import ei.algobaroapi.domain.compile.dto.request.CompileExecutionRequest;
import ei.algobaroapi.domain.compile.dto.response.CompileExecutionResponse;
import ei.algobaroapi.domain.compile.service.CompileService;
import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.global.config.swaggerdoc.CompileControllerDoc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CompileController implements CompileControllerDoc {

    private final CompileService compileService;

    @Override
    @PostMapping("/compile")
    @ResponseStatus(HttpStatus.CREATED)
    public CompileExecutionResponse compileCode(
            @AuthenticationPrincipal Member member,
            @RequestBody @Valid CompileExecutionRequest request
    ) {
        return compileService.executeCode(request);
    }
}
