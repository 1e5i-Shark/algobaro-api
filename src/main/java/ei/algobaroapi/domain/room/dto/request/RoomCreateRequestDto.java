package ei.algobaroapi.domain.room.dto.request;

import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.domain.room.domain.RoomAccessType;
import ei.algobaroapi.domain.room.domain.RoomStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "방 생성 요청 정보")
public class RoomCreateRequestDto {

    @NotNull
    @Schema(description = "방 상태", example = "RECRUITING")
    private RoomStatus roomStatus;

    @NotNull
    @Schema(description = "방 제목", example = "같이 푸실분~")
    private String title;

    @NotNull
    @Schema(description = "사용 가능 언어", example = "[\"JAVA\", \"C++\"]")
    private List<String> languages;

    @NotNull
    @Schema(description = "방 시작 시간", example = "2024-2-18T17:30:00")
    private LocalDateTime startAt;

    @NotNull
    @Schema(description = "방 접근 정보", example = "PRIVATE")
    private RoomAccessType roomAccessType;

    @NotNull
    @Schema(description = "문제 링크", example = "https://www.acmicpc.net/problem/1000")
    private String problemLink;

    @NotNull
    @Schema(description = "문제 플랫폼", example = "BOJ")
    private String problemPlatform;

    @Schema(description = "방 비밀번호", example = "password1234")
    private String password;

    @NotNull
    @Schema(description = "방 최대 인원", example = "4")
    private Integer roomLimit;

    @Schema(description = "태그", example = "[\"BFS\", \"Level 1\"]")
    private List<String> tags;

    @Schema(description = "타이머(Minute)", example = "20")
    private Integer timeLimit;

    public Room toEntity() {
        return Room.builder()
                .roomStatus(roomStatus)
                .title(title)
                .languages(languages)
                .startAt(startAt)
                .roomAccessType(roomAccessType)
                .problemLink(problemLink)
                .problemPlatform(problemPlatform)
                .password(password)
                .roomLimit(roomLimit)
                .tags(tags)
                .timeLimit(timeLimit)
                .build();
    }
}
