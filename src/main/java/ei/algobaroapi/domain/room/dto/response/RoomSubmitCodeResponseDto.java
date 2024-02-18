package ei.algobaroapi.domain.room.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "코드 제출 정보")
public class RoomSubmitCodeResponseDto {

    @Schema(description = "방 참여자 번호", example = "4")
    private String roomMemberId;

    @Schema(description = "참여자의 제출 코드",
            example = """
                    let input = require('fs').readFileSync('/dev/stdin').toString().split(' ');
                    const A = parseInt(input[0]);
                    const B = parseInt(input[1]);
                    console.log(A+B);
                    """
    )
    private String submitCode;

}
