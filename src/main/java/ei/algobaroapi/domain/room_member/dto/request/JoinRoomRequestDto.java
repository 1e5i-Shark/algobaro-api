package ei.algobaroapi.domain.room_member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Schema(description = "방 참여 요청 정보")
@NoArgsConstructor
@AllArgsConstructor
public class JoinRoomRequestDto {

    @Schema(description = "방 비밀번호", example = "password1234")
    private String password;
}
