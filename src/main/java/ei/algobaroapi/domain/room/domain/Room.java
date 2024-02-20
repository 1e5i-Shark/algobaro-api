package ei.algobaroapi.domain.room.domain;

import ei.algobaroapi.domain.room.dto.request.RoomUpdateRequestDto;
import ei.algobaroapi.global.entity.BaseEntity;
import ei.algobaroapi.global.util.StringListConverter;
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
import lombok.Builder;
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

    @Builder
    public Room(RoomStatus roomStatus, String title, String introduce, LocalDateTime startAt,
            RoomAccessType roomAccessType, String problemLink, String problemPlatform,
            String problemName, String password, int roomLimit, List<String> levelTag,
            List<String> algorithmTag, UUID roomUUID) {
        this.roomStatus = roomStatus;
        this.title = title;
        this.introduce = introduce;
        this.startAt = startAt;
        this.roomAccessType = roomAccessType;
        this.problemLink = problemLink;
        this.problemPlatform = problemPlatform;
        this.problemName = problemName;
        this.password = password;
        this.roomLimit = roomLimit;
        this.levelTag = levelTag;
        this.algorithmTag = algorithmTag;
        this.roomUUID = roomUUID;
    }

    public void update(RoomUpdateRequestDto roomUpdateRequestDto) {
        if (roomUpdateRequestDto.getTitle() != null) {
            this.title = roomUpdateRequestDto.getTitle();
        }
        if (roomUpdateRequestDto.getIntroduce() != null) {
            this.introduce = roomUpdateRequestDto.getIntroduce();
        }
        if (roomUpdateRequestDto.getStartAt() != null) {
            this.startAt = roomUpdateRequestDto.getStartAt();
        }
        if (roomUpdateRequestDto.getRoomAccessType() != null) {
            this.roomAccessType = roomUpdateRequestDto.getRoomAccessType();
        }
        if (roomUpdateRequestDto.getProblemLink() != null) {
            this.problemLink = roomUpdateRequestDto.getProblemLink();
        }
        if (roomUpdateRequestDto.getProblemPlatform() != null) {
            this.problemPlatform = roomUpdateRequestDto.getProblemPlatform();
        }
        if (roomUpdateRequestDto.getProblemName() != null) {
            this.problemName = roomUpdateRequestDto.getProblemName();
        }
        if (roomUpdateRequestDto.getPassword() != null) {
            this.password = roomUpdateRequestDto.getPassword();
        }
        if (roomUpdateRequestDto.getRoomLimit() > 0) {
            this.roomLimit = roomUpdateRequestDto.getRoomLimit();
        }
        if (roomUpdateRequestDto.getLevelTag() != null) {
            this.levelTag = roomUpdateRequestDto.getLevelTag();
        }
        if (roomUpdateRequestDto.getAlgorithmTag() != null) {
            this.algorithmTag = roomUpdateRequestDto.getAlgorithmTag();
        }
    }
}
