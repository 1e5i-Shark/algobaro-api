package ei.algobaroapi.domain.room.dto.request;

import ei.algobaroapi.domain.room.domain.RoomAccessType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "방 수정 요청 정보")
public class RoomUpdateRequestDto {

    @Schema(description = "방 제목", example = "같이 푸실분~")
    private String title;

    @Schema(description = "방 시작 시간", example = "2024-2-18T17:30:00")
    private LocalDateTime startAt;

    @Schema(description = "사용 가능 언어", example = "[\"JAVA\", \"C++\"]")
    private List<String> languages;

    @Schema(description = "방 접근 정보", example = "공개 방")
    private RoomAccessType roomAccessType;

    @Schema(description = "문제 링크", example = "https://www.acmicpc.net/problem/1000")
    private String problemLink;

    @Schema(description = "문제 플랫폼", example = "백준")
    private String problemPlatform;

    @Schema(description = "방 비밀번호", example = "password1234")
    private String password;

    @Schema(description = "방 최대 인원", example = "4")
    private Integer roomLimit;

    @Schema(description = "태그", example = "[\"BFS\", \"Level 2\"]")
    private List<String> tags;

    @Schema(description = "타이머(Minute)", example = "20")
    private Integer timeLimit;
}
