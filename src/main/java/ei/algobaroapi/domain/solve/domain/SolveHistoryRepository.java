package ei.algobaroapi.domain.solve.domain;

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

    @Query("select s from SolveHistory s where s.roomUuid like CONCAT(:roomShortUuid, '%') and s.member.id = :memberId")
    Optional<SolveHistory> findByMemberAndRoomUuidStartingWith(Long memberId, String roomShortUuid);

    @Query("select s from SolveHistory s join fetch s.member where s.roomUuid like CONCAT(:roomShortUuid, '%')")
    List<SolveHistory> findByRoomShortUuidWithMember(String roomShortUuid);
}
