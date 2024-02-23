package ei.algobaroapi.domain.solve.service;

import ei.algobaroapi.domain.solve.dto.request.BojCodeSubmissionRequest;
import ei.algobaroapi.domain.solve.dto.response.BojCodeSubmissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BojSolveServiceImpl implements SolveService {

    @Override
    public BojCodeSubmissionResponse submitCode(BojCodeSubmissionRequest request) {
        return null;
    }
}
