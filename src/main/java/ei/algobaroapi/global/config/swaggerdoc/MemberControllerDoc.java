package ei.algobaroapi.global.config.swaggerdoc;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.dto.request.MemberDetailUpdateRequest;
import ei.algobaroapi.domain.member.dto.response.MemberDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Member", description = "회원 관련 API")
@SuppressWarnings("unused")
public interface MemberControllerDoc {

    @Operation(summary = "내 정보 조회", description = "로그인 한 사용자의 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "사용자 정보 조회 성공")
    MemberDetailResponse getMyInfo(Member member);

    @Operation(summary = "내 정보 수정", description = "로그인 한 사용자의 정보를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "사용자 정보 수정 성공")
    @ApiResponse(responseCode = "E01201", description = "비밀번호가 일치하지 않습니다.")
    void updateMemberInfo(Member member, MemberDetailUpdateRequest memberUpdateRequest);
}
