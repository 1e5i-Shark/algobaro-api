package ei.algobaroapi.domain.room.domain;

import ei.algobaroapi.global.util.StringListConverter;
import ei.algobaroapi.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends BaseEntity {

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "room_status", nullable = false)
    private RoomStatus roomStatus;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "introduce", nullable = false)
    private String introduce;

    @Column(name = "start_at", columnDefinition = "datetime")
    private LocalDateTime startAt;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "room_access_type", nullable = false)
    private RoomAccessType roomAccessType;

    @Column(name = "problem_link")
    private String problemLink;

    @Column(name = "problem_platform")
    private String problemPlatform;

    @Column(name = "problem_name")
    private String problemName;

    @Column(name = "password")
    private String password;

    @Column(name = "room_limit", nullable = false)
    private int roomLimit;

    @Column(name = "level_tag")
    @Convert(converter = StringListConverter.class)
    private List<String> levelTag;

    @Column(name = "algorithm_tag")
    @Convert(converter = StringListConverter.class)
    private List<String> algorithmTag;

    @Column(name = "room_uuid", nullable = false)
    private UUID roomUUID;
}
