package ei.algobaroapi.domain.solve.service;

import ei.algobaroapi.domain.solve.domain.SolveHistory;
import ei.algobaroapi.domain.solve.dto.request.SolveHistoryListFindRequest;
import ei.algobaroapi.domain.solve.dto.response.SolveHistoryDetailResponse;
import ei.algobaroapi.domain.solve.dto.response.SolveHistoryResponse;
import ei.algobaroapi.domain.solve.dto.response.SolveResultResponse;
import ei.algobaroapi.global.dto.PageResponse;
import java.util.List;

public interface SolveHistoryService {


    PageResponse<SolveHistory, SolveHistoryResponse> getHistoryList(
            Long memberId,
            SolveHistoryListFindRequest request
    );

    SolveHistoryDetailResponse getHistoryDetail(Long memberId, Long solveId);

    void updateSolveHistoryCode(Long memberId, String roomShortUuid, String language, String code);

    SolveResultResponse getSolveResultInRoom(String roomShortUuid);

    void setUpSolveHistory(Long memberId, String roomUuid, String problemLink);

    List<SolveHistory> getSolveHistoryListByRoomShortUuid(String roomUuid);
}
