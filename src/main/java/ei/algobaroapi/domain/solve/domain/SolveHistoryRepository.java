package ei.algobaroapi.domain.solve.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolveHistoryRepository extends
        JpaRepository<SolveHistory, Long>,
        SolveHistoryRepositoryQuery {

}
