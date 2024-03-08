package ei.algobaroapi.domain.room_member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JoinRoomRequestDto {

    @Schema(description = "방 비밀번호", example = "password1234")
    private String password;
}
