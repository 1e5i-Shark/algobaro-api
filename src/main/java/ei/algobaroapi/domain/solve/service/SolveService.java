package ei.algobaroapi.domain.solve.service;

import ei.algobaroapi.domain.solve.dto.request.BojCodeSubmissionRequest;
import ei.algobaroapi.domain.solve.dto.request.CodeSubmissionRequest;
import ei.algobaroapi.domain.solve.dto.response.BojCodeSubmissionResponse;
import ei.algobaroapi.domain.solve.dto.response.CodeSubmissionResponse;

public interface SolveService {

    CodeSubmissionResponse submitCode(Long memberId, CodeSubmissionRequest request);

    BojCodeSubmissionResponse submitCodeAndCompile(Long memberId, BojCodeSubmissionRequest request);
}
