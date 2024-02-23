package ei.algobaroapi.domain.room.dto.response;

import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.domain.room.domain.RoomAccessType;
import ei.algobaroapi.domain.room.domain.RoomStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "메인 화면에 보여지는 방 정보")
public class RoomDetailResponseDto {

    @Schema(description = "방 번호", example = "1")
    private Long roomId;

    @Schema(description = "방 상태", example = "문제 푸는 중")
    private RoomStatus roomStatus;

    @Schema(description = "방 제목", example = "같이 푸실분~")
    private String title;

    @Schema(description = "방 소개", example = "저랑 같이 A+B 문제 푸실 분 구해요")
    private String introduce;

    @Schema(description = "방 접근 정보", example = "공개 방")
    private RoomAccessType roomAccessType;

    @Schema(description = "문제 플랫폼", example = "백준")
    private String problemPlatform;

    @Schema(description = "문제 이름", example = "A+B")
    private String problemName;

    @Schema(description = "방 비밀번호", example = "password1234")
    private String password;

    @Schema(description = "방 최대 인원", example = "4")
    private int roomLimit;

    @Schema(description = "문제 레벨", example = "Gold 4")
    private List<String> levelTag;

    @Schema(description = "문제 알고리즘 종류", example = "BFS")
    private List<String> algorithmTag;

    // TODO: short UUID 전달

    public static RoomDetailResponseDto of(Room room) {
        return new RoomDetailResponseDto(
                room.getId(),
                room.getRoomStatus(),
                room.getTitle(),
                room.getIntroduce(),
                room.getRoomAccessType(),
                room.getProblemPlatform(),
                room.getProblemName(),
                room.getPassword(),
                room.getRoomLimit(),
                room.getLevelTag(),
                room.getAlgorithmTag()
        );
    }
}


