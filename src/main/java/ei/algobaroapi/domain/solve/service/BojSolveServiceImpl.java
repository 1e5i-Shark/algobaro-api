package ei.algobaroapi.domain.solve.service;

import ei.algobaroapi.domain.compile.dto.request.CompileExecutionRequest;
import ei.algobaroapi.domain.compile.dto.response.CompileExecutionResponse;
import ei.algobaroapi.domain.compile.service.CompileService;
import ei.algobaroapi.domain.problem.dto.request.ProblemFindRequest;
import ei.algobaroapi.domain.problem.dto.response.ProblemTestCaseResponse;
import ei.algobaroapi.domain.problem.service.ProblemService;
import ei.algobaroapi.domain.solve.dto.BojTestCaseResult;
import ei.algobaroapi.domain.solve.dto.request.BojCodeSubmissionRequest;
import ei.algobaroapi.domain.solve.dto.request.CodeSubmissionRequest;
import ei.algobaroapi.domain.solve.dto.response.BojCodeSubmissionResponse;
import ei.algobaroapi.domain.solve.dto.response.CodeSubmissionResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BojSolveServiceImpl implements SolveService {

    private final ProblemService problemService;
    private final CompileService compileService;
    private final SolveHistoryService solveHistoryService;

    @Override
    public CodeSubmissionResponse submitCode(Long memberId, CodeSubmissionRequest request) {
        solveHistoryService.updateSolveHistoryCode(
                memberId,
                request.getRoomShortUuid(),
                request.getLanguage(),
                request.getCode()
        );

        return CodeSubmissionResponse.of(request.getCode());
    }

    @Override
    public BojCodeSubmissionResponse submitCodeAndCompile(Long memberId,
            BojCodeSubmissionRequest request) {
        List<ProblemTestCaseResponse> problemTestCases = problemService.getProblemTestCases(
                ProblemFindRequest.builder()
                        .problemLink(request.getProblemLink())
                        .build()
        );

        List<BojTestCaseResult> testCaseResults = runAllTestCases(
                problemTestCases,
                request.getLanguage(),
                request.getCode()
        );

        solveHistoryService.updateSolveHistoryCode(
                memberId,
                request.getRoomShortUuid(),
                request.getLanguage(),
                request.getCode()
        );

        return BojCodeSubmissionResponse.of(testCaseResults);
    }

    private List<BojTestCaseResult> runAllTestCases(
            List<ProblemTestCaseResponse> problemTestCases,
            String language,
            String code
    ) {
        return problemTestCases.stream()
                .map(problemTestCase -> runEachTestCase(
                        problemTestCase,
                        language,
                        code
                ))
                .toList();
    }

    private BojTestCaseResult runEachTestCase(
            ProblemTestCaseResponse problemTestCase,
            String language,
            String code
    ) {
        CompileExecutionResponse executionResult = compileService.executeCode(
                CompileExecutionRequest.builder()
                        .language(language)
                        .input(problemTestCase.getInput())
                        .code(code)
                        .build()
        );

        return BojTestCaseResult.builder()
                .caseNumber(problemTestCase.getCaseNumber())
                .input(problemTestCase.getInput())
                .output(problemTestCase.getOutput())
                .result(executionResult.getResult())
                .build();
    }
}
