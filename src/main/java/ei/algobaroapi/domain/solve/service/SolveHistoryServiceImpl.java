package ei.algobaroapi.domain.solve.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.service.MemberService;
import ei.algobaroapi.domain.problem.dto.request.ProblemSolveRequest;
import ei.algobaroapi.domain.problem.service.ProblemService;
import ei.algobaroapi.domain.solve.domain.SolveHistory;
import ei.algobaroapi.domain.solve.domain.SolveHistoryRepository;
import ei.algobaroapi.domain.solve.domain.SolveStatus;
import ei.algobaroapi.domain.solve.dto.request.SolveHistoryListFindRequest;
import ei.algobaroapi.domain.solve.dto.response.SolveHistoryDetailResponse;
import ei.algobaroapi.domain.solve.dto.response.SolveHistoryResponse;
import ei.algobaroapi.domain.solve.dto.response.SolveResult;
import ei.algobaroapi.domain.solve.dto.response.SolveResultResponse;
import ei.algobaroapi.domain.solve.exception.SolveAccessException;
import ei.algobaroapi.domain.solve.exception.SolveFoundException;
import ei.algobaroapi.domain.solve.exception.common.SolveErrorCode;
import ei.algobaroapi.global.dto.PageResponse;
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

    private final ProblemService problemService;
    private final MemberService memberService;
    private final SolveHistoryRepository solveHistoryRepository;

    @Override
    public PageResponse<SolveHistory, SolveHistoryResponse> getHistoryList(
            Long memberId,
            SolveHistoryListFindRequest request
    ) {
        return PageResponse.of(
                solveHistoryRepository.findListPage(
                        request,
                        PageRequest.of(request.getPage(), request.getSize()),
                        memberId
                ),
                SolveHistoryResponse::of
        );
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

    @Override
    public SolveResultResponse getSolveResultInRoom(String roomUuid) {
        List<SolveHistory> solveHistoryList = this.getSolveHistoryList(roomUuid);

        List<SolveResult> solveResults = solveHistoryList.stream()
                .map(solveHistory ->
                        SolveResult.builder()
                                .memberId(solveHistory.getMember().getId())
                                .language(solveHistory.getCodeLanguage())
                                .code(solveHistory.getInputCode())
                                .solveStatus(solveHistory.getSolveStatus())
                                .build())
                .toList();

        return SolveResultResponse.of(solveResults);
    }

    @Override
    public void setUpSolveHistory(Long memberId, String roomUuid, String problemLink) {
        SolveHistory createSolveHistory = SolveHistory.builder()
                .member(memberService.getMemberById(memberId))
                .roomUuid(roomUuid)
                .problemLink(problemLink)
                .build();

        solveHistoryRepository.save(createSolveHistory);
    }

    @Override
    public List<SolveHistory> getSolveHistoryList(String roomUuid) {
        return solveHistoryRepository.findByRoomUuidWithMember(roomUuid);
    }
}
