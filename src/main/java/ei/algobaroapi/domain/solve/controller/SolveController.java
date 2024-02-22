package ei.algobaroapi.domain.solve.controller;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.solve.dto.request.BojCodeSubmissionRequest;
import ei.algobaroapi.domain.solve.dto.request.SolveHistoryListFindRequest;
import ei.algobaroapi.domain.solve.dto.response.BojCodeSubmissionResponse;
import ei.algobaroapi.domain.solve.dto.response.SolveHistoryDetailResponse;
import ei.algobaroapi.domain.solve.dto.response.SolveHistoryResponse;
import ei.algobaroapi.global.config.swaggerdoc.SolveControllerDoc;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SolveController implements SolveControllerDoc {

    @Override
    @PostMapping("/solves/submission")
    public BojCodeSubmissionResponse submissionCode(
            @AuthenticationPrincipal Member member,
            @RequestBody @Valid BojCodeSubmissionRequest request
    ) {
        return null;
    }

    @Override
    @GetMapping("/solves/history")
    public List<SolveHistoryResponse> getHistoryList(
            @AuthenticationPrincipal Member member,
            @ModelAttribute @Valid SolveHistoryListFindRequest request
    ) {
        return Collections.emptyList();
    }

    @Override
    @GetMapping("/solves/history/{solveId}")
    public SolveHistoryDetailResponse getHistory(
            @AuthenticationPrincipal Member member,
            @PathVariable(name = "solveId") Long solveId
    ) {
        return null;
    }
}
