package ei.algobaroapi.domain.problem.service;

import ei.algobaroapi.domain.problem.dto.request.ProblemFindRequest;
import ei.algobaroapi.domain.problem.dto.response.ProblemHtmlResponse;
import ei.algobaroapi.domain.problem.dto.response.ProblemTestCaseResponse;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BojProblemServiceImpl implements ProblemService {

    @Override
    public ProblemHtmlResponse getProblemInfoHtml(ProblemFindRequest request) {
        return null;
    }

    @Override
    public List<ProblemTestCaseResponse> getProblemTestCases(ProblemFindRequest request) {
        return Collections.emptyList();
    }
}
