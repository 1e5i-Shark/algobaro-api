package ei.algobaroapi.domain.problem.service;

import ei.algobaroapi.domain.problem.dto.request.ProblemFindRequest;
import ei.algobaroapi.domain.problem.dto.request.ProblemSolveRequest;
import ei.algobaroapi.domain.problem.dto.response.ProblemHtmlResponse;
import ei.algobaroapi.domain.problem.dto.response.ProblemTestCaseResponse;
import ei.algobaroapi.domain.solve.domain.SolveStatus;
import java.util.List;

public interface ProblemService {

    ProblemHtmlResponse getProblemInfoHtml(ProblemFindRequest request);

    List<ProblemTestCaseResponse> getProblemTestCases(ProblemFindRequest request);

    SolveStatus checkSolveResult(ProblemSolveRequest request);
}
