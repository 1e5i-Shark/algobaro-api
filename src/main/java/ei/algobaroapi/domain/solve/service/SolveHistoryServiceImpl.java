package ei.algobaroapi.domain.solve.service;

import ei.algobaroapi.domain.solve.domain.SolveHistoryRepository;
import ei.algobaroapi.domain.solve.dto.request.SolveHistoryListFindRequest;
import ei.algobaroapi.domain.solve.dto.response.SolveHistoryDetailResponse;
import ei.algobaroapi.domain.solve.dto.response.SolveHistoryResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SolveHistoryServiceImpl implements SolveHistoryService {

    private final SolveHistoryRepository solveHistoryRepository;

    @Override
    public List<SolveHistoryResponse> getHistoryList(
            Long memberId,
            SolveHistoryListFindRequest request
    ) {
        PageRequest pageable = PageRequest.of(request.getPage(), request.getSize());
        return solveHistoryRepository.findListPage(
                        request,
                        pageable,
                        memberId
                )
                .map(SolveHistoryResponse::of)
                .getContent();
    }

    @Override
    public SolveHistoryDetailResponse getHistoryDetail(Long memberId, Long solveId) {
        return null;
    }
}
