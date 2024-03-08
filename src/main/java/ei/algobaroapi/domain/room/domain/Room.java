package ei.algobaroapi.domain.room.domain;

import ei.algobaroapi.domain.room.dto.request.RoomUpdateRequestDto;
import ei.algobaroapi.domain.room_member.domain.RoomMember;
import ei.algobaroapi.global.entity.BaseEntity;
import ei.algobaroapi.global.util.StringListConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Column(name = "languages")
    @Convert(converter = StringListConverter.class)
    private List<String> languages;

    @Column(name = "start_at", columnDefinition = "datetime")
    private LocalDateTime startAt;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "room_access_type", nullable = false)
    private RoomAccessType roomAccessType;

    @Column(name = "problem_link")
    private String problemLink;

    @Column(name = "problem_platform")
    private String problemPlatform;

    @Column(name = "password")
    private String password;

    @Column(name = "room_limit", nullable = false)
    private Integer roomLimit;

    @Column(name = "time_limit", nullable = false)
    private Integer timeLimit;

    @Column(name = "tags")
    @Convert(converter = StringListConverter.class)
    private List<String> tags;

    @Column(name = "room_uuid", nullable = false)
    private String roomUuid;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<RoomMember> roomMembers = new ArrayList<>();

    @Builder
    public Room(RoomStatus roomStatus, String title, List<String> languages, LocalDateTime startAt,
            RoomAccessType roomAccessType, String problemLink, String problemPlatform,
            String password, Integer roomLimit, List<String> tags,
            Integer timeLimit) {
        this.roomStatus = roomStatus;
        this.title = title;
        this.languages = languages;
        this.startAt = startAt;
        this.roomAccessType = roomAccessType;
        this.problemLink = problemLink;
        this.problemPlatform = problemPlatform;
        this.password = password;
        this.roomLimit = roomLimit;
        this.tags = tags;
        this.timeLimit = timeLimit;
        this.roomUuid = UUID.randomUUID().toString();
    }

    public void update(RoomUpdateRequestDto roomUpdateRequestDto) {
        if (roomUpdateRequestDto.getTitle() != null) {
            this.title = roomUpdateRequestDto.getTitle();
        }
        if (roomUpdateRequestDto.getLanguages() != null) {
            this.languages = roomUpdateRequestDto.getLanguages();
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
        if (roomUpdateRequestDto.getPassword() != null) {
            this.password = roomUpdateRequestDto.getPassword();
        }
        if (roomUpdateRequestDto.getRoomLimit() > 0) {
            this.roomLimit = roomUpdateRequestDto.getRoomLimit();
        }
        if (roomUpdateRequestDto.getTags() != null) {
            this.tags = roomUpdateRequestDto.getTags();
        }
        if (roomUpdateRequestDto.getRoomLimit() != 0) {
            this.roomLimit = roomUpdateRequestDto.getRoomLimit();
        }
    }

    public String getRoomShortUuid() {
        return this.roomUuid.split("-")[0];
    }

    public void updateRoomStatusRunning() {
        this.roomStatus = RoomStatus.RUNNING;
    }
}
