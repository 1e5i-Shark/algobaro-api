package ei.algobaroapi.domain.solve.domain;

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
}
