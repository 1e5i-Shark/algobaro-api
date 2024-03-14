package ei.algobaroapi.domain.solve.domain;

import ei.algobaroapi.domain.member.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SolveHistoryRepository extends
        JpaRepository<SolveHistory, Long>,
        SolveHistoryRepositoryQuery {

    @Query("select s from SolveHistory s join fetch s.member where s.id = :solveId")
    Optional<SolveHistory> findByIdWithMember(Long solveId);

    @Query("select s from SolveHistory s where s.roomUuid like CONCAT(:roomShortUuid, '%') and s.member = :member")
    Optional<SolveHistory> findByMemberAndRoomUuidStartingWith(Member member, String roomShortUuid);

    @Query("select s from SolveHistory s join fetch s.member where s.roomUuid = :roomUuid")
    List<SolveHistory> findByRoomUuidWithMember(String roomUuid);
}
