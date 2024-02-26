package ei.algobaroapi.domain.problem.service;

import ei.algobaroapi.domain.problem.dto.request.ProblemFindRequest;
import ei.algobaroapi.domain.problem.dto.request.ProblemSolveRequest;
import ei.algobaroapi.domain.problem.dto.response.ProblemHtmlResponse;
import ei.algobaroapi.domain.problem.dto.response.ProblemTestCaseResponse;
import ei.algobaroapi.domain.problem.exception.CrawlingAccessException;
import ei.algobaroapi.domain.problem.exception.common.ProblemErrorCode;
import ei.algobaroapi.domain.solve.domain.SolveStatus;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BojProblemServiceImpl implements ProblemService {

    private static final String PREFIX = "https://www.acmicpc.net/status?";
    private static final String CORRECT = "맞았습니다!!";

    @Override
    public ProblemHtmlResponse getProblemInfoHtml(ProblemFindRequest request) {
        try {
            return ProblemHtmlResponse.of(
                    Jsoup.connect(request.getProblemLink()).get().outerHtml());
        } catch (IOException e) {
            throw CrawlingAccessException.of(ProblemErrorCode.CRAWLING_NOT_ACCESS);
        }
    }

    @Override
    public List<ProblemTestCaseResponse> getProblemTestCases(ProblemFindRequest request) {
        return Collections.emptyList();
    }

    @Override
    public SolveStatus checkSolveResult(ProblemSolveRequest request) {
        SolveStatus answer;

        try {
            String problemNumber = request.getProblemLink()
                    .substring(request.getProblemLink().lastIndexOf("/") + 1);
            String userBojId = request.getUserBojId();

            String url = PREFIX + "problem_id=" + problemNumber + "&" + "user_id=" + userBojId;

            // Jsoup을 사용하여 웹페이지 가져오기
            Document doc = Jsoup.connect(url).get();

            // 결과 테이블 가져오기
            Element table = doc.selectFirst("#status-table");

            // 결과 문자열 parsing
            String result = Objects.requireNonNull(Objects.requireNonNull(
                            Objects.requireNonNull(table).selectFirst("td.result"))
                    .selectFirst("span.result-text")).text();

            answer = result.equals(CORRECT) ? SolveStatus.SUCCESS : SolveStatus.FAIL;

        } catch (IOException e) {
            throw CrawlingAccessException.of(ProblemErrorCode.CRAWLING_NOT_ACCESS);
        }

        return answer;
    }
}
