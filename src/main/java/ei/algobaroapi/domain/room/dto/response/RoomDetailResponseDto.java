package ei.algobaroapi.domain.room.dto.response;

import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.domain.room.domain.RoomAccessType;
import ei.algobaroapi.domain.room.domain.RoomStatus;
import ei.algobaroapi.domain.room_member.dto.response.RoomMemberResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "방 참여자가 포함된 개별 상세 정보")
public class RoomDetailResponseDto {

    @Schema(description = "방 번호", example = "1")
    private Long roomId;

    @Schema(description = "방 상태", example = "문제 푸는 중")
    private RoomStatus roomStatus;

    @Schema(description = "방 제목", example = "같이 푸실분~")
    private String title;

    @Schema(description = "방 접근 정보", example = "공개 방")
    private RoomAccessType roomAccessType;

    @Schema(description = "문제 플랫폼", example = "백준")
    private String problemPlatform;

    @Schema(description = "방 비밀번호", example = "password1234")
    private String password;

    @Schema(description = "방 최대 인원", example = "4")
    private Integer roomLimit;

    @Schema(description = "태그", example = "[\"BFS\", \"Level 1\"]")
    private List<String> tags;

    @Schema(description = "타이머(Minute)", example = "20")
    private Integer timeLimit;

    @Schema(description = "방 short UUID", example = "2ad2e9db")
    private String roomShortUuid;

    @Schema(description = "방 참여자 정보", example =
            """
                    [
                        {
                            "id": "test1@test.com",
                            "nickname": "test1",
                            "profileImage": null,
                            "role": "HOST",
                            "joinTime": "2024-03-04T00:45:18",
                            "ready": true
                        },
                        {
                            "id": "test2@test.com",
                            "nickname": "test2",
                            "profileImage": null,
                            "role": "PARTICIPANT",
                            "joinTime": "2024-03-04T00:45:36",
                            "ready": false
                        }
                    ]""")
    private List<RoomMemberResponseDto> roomMembers;

    public static RoomDetailResponseDto of(Room room, List<RoomMemberResponseDto> roomMembers) {
        return new RoomDetailResponseDto(
                room.getId(),
                room.getRoomStatus(),
                room.getTitle(),
                room.getRoomAccessType(),
                room.getProblemPlatform(),
                room.getPassword(),
                room.getRoomLimit(),
                room.getTags(),
                room.getTimeLimit(),
                room.getRoomUuid().split("-")[0],
                roomMembers
        );
    }
}


