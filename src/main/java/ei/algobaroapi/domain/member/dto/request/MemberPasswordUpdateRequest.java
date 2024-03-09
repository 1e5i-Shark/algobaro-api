package ei.algobaroapi.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "회원 비밀번호 수정 요청 정보")
public class MemberPasswordUpdateRequest {

    @Schema(description = "변경 할 패스워드", example = "password1234!")
    private String newPassword;

    @Schema(description = "현재 패스워드", example = "password1234!")
    private String currentPassword;
}
