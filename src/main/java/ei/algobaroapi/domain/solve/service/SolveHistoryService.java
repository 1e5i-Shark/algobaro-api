package ei.algobaroapi.domain.solve.service;

import ei.algobaroapi.domain.solve.dto.request.SolveHistoryListFindRequest;
import ei.algobaroapi.domain.solve.dto.response.SolveHistoryDetailResponse;
import ei.algobaroapi.domain.solve.dto.response.SolveHistoryResponse;
import ei.algobaroapi.domain.solve.dto.response.SolveResultResponse;
import java.util.List;

public interface SolveHistoryService {


    List<SolveHistoryResponse> getHistoryList(Long memberId, SolveHistoryListFindRequest request);

    SolveHistoryDetailResponse getHistoryDetail(Long memberId, Long solveId);

    void completeSolveHistory(String roomUuid);

    void updateSolveHistoryCode(Long memberId, String roomUuid, String language, String code);

    void completeSolveHistory(String roomUuid);

    SolveResultResponse getSolveResultInRoom(String roomUuid);
}
