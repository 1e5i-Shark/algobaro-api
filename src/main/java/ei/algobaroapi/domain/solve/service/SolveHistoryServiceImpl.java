package ei.algobaroapi.domain.solve.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.service.MemberService;
import ei.algobaroapi.domain.solve.domain.SolveHistory;
import ei.algobaroapi.domain.solve.domain.SolveHistoryRepository;
import ei.algobaroapi.domain.solve.dto.request.SolveHistoryListFindRequest;
import ei.algobaroapi.domain.solve.dto.response.SolveHistoryDetailResponse;
import ei.algobaroapi.domain.solve.dto.response.SolveHistoryResponse;
import ei.algobaroapi.domain.solve.exception.SolveAccessException;
import ei.algobaroapi.domain.solve.exception.SolveFoundException;
import ei.algobaroapi.domain.solve.exception.common.SolveErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SolveHistoryServiceImpl implements SolveHistoryService {

    private final MemberService memberService;
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
        SolveHistory findHistory = this.getSolveHistoryById(solveId);

        checkMemberId(memberId, findHistory);

        return SolveHistoryDetailResponse.of(findHistory);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateSolveHistoryCode(
            Long memberId,
            String roomUuid,
            String language,
            String code
    ) {
        Member findMember = memberService.getMemberById(memberId);
        SolveHistory findSolveHistory = getSolveHistoryByMemberAndRoomUuid(roomUuid, findMember);

        findSolveHistory.updateCodeAndLanguage(code, language);
    }

    private SolveHistory getSolveHistoryByMemberAndRoomUuid(String roomUuid, Member findMember) {
        return solveHistoryRepository.findByMemberAndRoomUuid(findMember, roomUuid)
                .orElseThrow(() -> SolveFoundException.of(SolveErrorCode.SOLVE_HISTORY_NOT_FOUND));
    }

    private void checkMemberId(Long memberId, SolveHistory findHistory) {
        if (!findHistory.getMember().getId().equals(memberId)) {
            throw SolveAccessException.of(SolveErrorCode.SOLVE_HISTORY_ACCESS_DENIED);
        }
    }

    private SolveHistory getSolveHistoryById(Long solveId) {
        return solveHistoryRepository.findByIdWithMember(solveId)
                .orElseThrow(() -> SolveFoundException.of(SolveErrorCode.SOLVE_HISTORY_NOT_FOUND));
    }
}
