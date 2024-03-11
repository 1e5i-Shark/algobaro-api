package ei.algobaroapi.domain.solve.domain;

import ei.algobaroapi.domain.solve.dto.request.SolveHistoryListFindRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SolveHistoryRepositoryQuery {

    Page<SolveHistory> findListPage(
            SolveHistoryListFindRequest request,
            Pageable pageable,
            Long memberId
    );
}
