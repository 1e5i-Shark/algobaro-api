package ei.algobaroapi.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "회원 정보 수정 요청 정보")
@Getter
@Builder
public class MemberDetailUpdateRequest {

    @Schema(description = "회원 닉네임", example = "test_nickname")
    private String nickname;
    @Schema(description = "회원 BOJ id", example = "test_boj_id")
    private String bojId;
    @Schema(description = "변경 할 패스워드", example = "password1234!")
    private String newPassword;
    @Schema(description = "현재 패스워드", example = "password1234!")
    private String currentPassword;
}
