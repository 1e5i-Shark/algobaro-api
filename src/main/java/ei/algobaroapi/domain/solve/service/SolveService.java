package ei.algobaroapi.domain.solve.service;

import ei.algobaroapi.domain.solve.dto.request.BojCodeSubmissionRequest;
import ei.algobaroapi.domain.solve.dto.response.BojCodeSubmissionResponse;


public interface SolveService {

    BojCodeSubmissionResponse submitCode(BojCodeSubmissionRequest request);
}
