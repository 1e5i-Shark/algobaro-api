package ei.algobaroapi.domain.room.domain;

import static ei.algobaroapi.domain.room.domain.QRoom.room;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ei.algobaroapi.domain.room.dto.request.RoomListRequestDto;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class RoomRepositoryQueryImpl implements RoomRepositoryQuery {

    private static final NumberExpression<Integer> roomStatusPriority = new CaseBuilder()
            .when(room.roomStatus.eq(RoomStatus.RECRUITING)).then(1)
            .when(room.roomStatus.eq(RoomStatus.RUNNING)).then(2)
            .otherwise(3);
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Room> findListPage(RoomListRequestDto request, Pageable pageable) {
        BooleanExpression booleanExpression = room.deletedAt.isNull()
                .and(titleContainsIgnoreCase(request.getSearchTitle()))
                .and(roomStatusEq(request.getRoomStatus()))
                .and(roomAccessTypeEq(request.getRoomAccessType()))
                .and(roomMemberNotZero())
                .and(languageContains(request.getLanguages()));

        List<Room> content = jpaQueryFactory
                .selectFrom(room)
                .leftJoin(room.roomMembers)
                .where(booleanExpression)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(roomStatusPriority.asc(), room.createdAt.desc())
                .distinct()
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(room.count())
                .from(room)
                .where(booleanExpression);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression titleContainsIgnoreCase(String keyword) {
        return room.title.containsIgnoreCase(Objects.requireNonNullElse(keyword, ""));
    }

    private BooleanExpression roomStatusEq(RoomStatus roomStatus) {
        if (roomStatus == RoomStatus.RECRUITING) {
            return room.roomStatus.eq(RoomStatus.RECRUITING);
        } else if (roomStatus == RoomStatus.RUNNING) {
            return room.roomStatus.eq(RoomStatus.RUNNING);
        } else {
            return room.roomStatus.isNotNull();
        }
    }

    private BooleanExpression roomAccessTypeEq(RoomAccessType roomAccessType) {
        if (roomAccessType == RoomAccessType.PRIVATE) {
            return room.roomAccessType.eq(RoomAccessType.PRIVATE);
        } else if (roomAccessType == RoomAccessType.PUBLIC) {
            return room.roomAccessType.eq(RoomAccessType.PUBLIC);
        } else {
            return room.roomAccessType.isNotNull();
        }
    }

    private BooleanExpression roomMemberNotZero() {
        return room.roomMembers.size().gt(0);
    }

    private BooleanExpression languageContains(List<String> languages) {
        if (languages == null || languages.isEmpty()) {
            return null;
        }

        StringTemplate template = Expressions.stringTemplate("{0}", room.languages);

        BooleanExpression languageCondition = null;

        for (String language : languages) {
            String languageWithQuotation = "\"" + language + "\"";
            BooleanExpression languageContainsExpression = template.containsIgnoreCase(
                    languageWithQuotation
            );
            if (languageCondition == null) {
                languageCondition = languageContainsExpression;
            } else {
                languageCondition = languageCondition.or(
                        languageContainsExpression
                );
            }
        }

        return languageCondition;
    }
}
