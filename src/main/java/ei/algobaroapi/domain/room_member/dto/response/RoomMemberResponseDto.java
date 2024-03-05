package ei.algobaroapi.domain.room_member.dto.response;

import ei.algobaroapi.domain.room_member.domain.RoomMember;
import ei.algobaroapi.domain.room_member.domain.RoomMemberRole;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "방 참여자 정보")
public class RoomMemberResponseDto {

    @Schema(description = "아이디", example = "test1@test.com")
    private String id;

    @Schema(description = "닉네임", example = "test1")
    private String nickname;

    @Schema(description = "회원 프로필 이미지", example = "https://image.url.com/test_profile_image")
    private String profileImage;

    @Schema(description = "준비 상태", example = "false")
    private boolean isReady;

    @Schema(description = "방장 여부", example = "HOST")
    private RoomMemberRole role;

    @Schema(description = "입장 시간", example = "2024-03-04 12:00:00")
    private LocalDateTime joinTime;

    public static RoomMemberResponseDto of(RoomMember roomMember) {
        return RoomMemberResponseDto.builder()
                .id(roomMember.getMember().getEmail().getEmail())
                .nickname(roomMember.getMember().getNickname())
                .profileImage(roomMember.getMember().getProfileImage())
                .isReady(roomMember.isReady())
                .role(roomMember.getRoomMemberRole())
                .joinTime(roomMember.getCreatedAt())
                .build();
    }
}
