package ei.algobaroapi.domain.solve.domain;

import static ei.algobaroapi.domain.solve.domain.QSolveHistory.solveHistory;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ei.algobaroapi.domain.solve.dto.request.SolveHistoryListFindRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class SolveHistoryRepositoryQueryImpl implements SolveHistoryRepositoryQuery {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<SolveHistory> findListPage(SolveHistoryListFindRequest request, Pageable pageable, Long memberId) {
        BooleanExpression booleanExpression = memberIdEq(memberId);

        List<SolveHistory> content = jpaQueryFactory
                .selectFrom(solveHistory)
                .leftJoin(solveHistory.member).fetchJoin()
                .where(booleanExpression)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(solveHistory.count())
                .from(solveHistory)
                .where(booleanExpression);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private static BooleanExpression memberIdEq(Long memberId) {
        return solveHistory.member.id.eq(memberId);
    }
}
