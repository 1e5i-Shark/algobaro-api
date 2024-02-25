package ei.algobaroapi.domain.solve.domain;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "solve_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SolveHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "room_uuid", nullable = false)
    private String roomUuid;

    @Column(name = "input_code", nullable = false, columnDefinition = "TEXT")
    private String inputCode;

    @Column(name = "code_language", nullable = false)
    private String codeLanguage;

    @Column(name = "start_at", nullable = false, columnDefinition = "datetime")
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false, columnDefinition = "datetime")
    private LocalDateTime endAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "solve_status", nullable = false)
    private SolveStatus solveStatus;

    @Column(name = "problem_link", nullable = false)
    private String problemLink;

    @Column(name = "problem_name", nullable = false)
    private String problemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "problem_platform", nullable = false)
    private ProblemPlatform problemPlatform;

    @Column(name = "problem_level", nullable = false)
    private String problemLevel;

    public void complete(SolveStatus solveStatus) {
        this.solveStatus = solveStatus;
        this.endAt = LocalDateTime.now();
    }

    public void updateCodeAndLanguage(String code, String language) {
        this.inputCode = code;
        this.codeLanguage = language;
    }

    public void complete(SolveStatus solveStatus) {
        this.solveStatus = solveStatus;
        this.endAt = LocalDateTime.now();
    }
}
