package ei.algobaroapi.domain.member.domain;

import ei.algobaroapi.domain.member.domain.vo.EmailVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.roles WHERE m.email = :email AND m.deletedAt IS NULL")
    Optional<Member> findByEmailAndDeletedAtIsNull(EmailVo email);
}
