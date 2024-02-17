package ei.algobaroapi.domain.member.dto.response;

import ei.algobaroapi.domain.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "회원 상세 정보")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDetailResponse {

    @Schema(description = "회원 식별 id", example = "1")
    private final Long id;

    @Schema(description = "회원 이메일", example = "test@test.com")
    private final String email;

    @Schema(description = "회원 닉네임", example = "test_nickname")
    private final String nickname;

    @Schema(description = "회원 BOJ id", example = "test_boj_id")
    private final String bojId;

    @Schema(description = "회원 프로필 이미지", example = "https://image.url.com/test_profile_image")
    private final String profileImage;

    public static MemberDetailResponse of(Member member) {
        return new MemberDetailResponse(
                member.getId(),
                member.getEmail().getEmail(),
                member.getNickname(),
                member.getBojId(),
                member.getProfileImage()
        );
    }
}
