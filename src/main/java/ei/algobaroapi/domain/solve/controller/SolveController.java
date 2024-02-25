package ei.algobaroapi.domain.solve.controller;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.solve.dto.request.BojCodeSubmissionRequest;
import ei.algobaroapi.domain.solve.dto.request.SolveHistoryListFindRequest;
import ei.algobaroapi.domain.solve.dto.response.BojCodeSubmissionResponse;
import ei.algobaroapi.domain.solve.dto.response.SolveHistoryResponse;
import ei.algobaroapi.domain.solve.service.SolveHistoryService;
import ei.algobaroapi.domain.solve.service.SolveService;
import ei.algobaroapi.global.config.swaggerdoc.SolveControllerDoc;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SolveController implements SolveControllerDoc {

    private final SolveService solveService;
    private final SolveHistoryService solveHistoryService;

    @Override
    @PostMapping("/solves/submission")
    @PreAuthorize("hasRole('ROLE_USER')")
    public BojCodeSubmissionResponse submissionCode(
            @AuthenticationPrincipal Member member,
            @RequestBody @Valid BojCodeSubmissionRequest request
    ) {
        return solveService.submitCode(member.getId(), request);
    }

    @Override
    @GetMapping("/solves/history")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<SolveHistoryResponse> getHistoryList(
            @AuthenticationPrincipal Member member,
            @ModelAttribute @Valid SolveHistoryListFindRequest request
    ) {
        return solveHistoryService.getHistoryList(member.getId(), request);
    }
}
