package ei.algobaroapi.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "회원 일반 정보 수정 요청 정보")
public class MemberGeneralUpdateRequest {

    @Schema(description = "회원 닉네임", example = "test_nickname")
    private String nickname;

    @Schema(description = "회원 BOJ id", example = "test_boj_id")
    private String bojId;
}
