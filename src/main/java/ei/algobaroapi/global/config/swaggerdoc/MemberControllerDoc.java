package ei.algobaroapi.global.config.swaggerdoc;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.dto.request.MemberGeneralUpdateRequest;
import ei.algobaroapi.domain.member.dto.request.MemberPasswordUpdateRequest;
import ei.algobaroapi.domain.member.dto.response.MemberDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Member", description = "회원 관련 API")
@SuppressWarnings("unused")
public interface MemberControllerDoc {

    @Operation(summary = "내 정보 조회", description = "로그인 한 사용자의 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "사용자 정보 조회 성공")
    MemberDetailResponse getMyInfo(Member member);

    @Operation(summary = "내 프로필 이미지 수정", description = "로그인 한 사용자의 프로필 이미지를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "프로필 이미지 수정 성공")
    @ApiResponse(responseCode = "E06401", description = "업로드한 파일이 비어있습니다.")
    @ApiResponse(responseCode = "E06402", description = "이미지 업로드 중 오류가 발생했습니다.")
    @ApiResponse(responseCode = "E06403", description = "올바른 파일 확장자가 없습니다.")
    @ApiResponse(responseCode = "E06404", description = "허용되지 않는 파일 확장자 입니다.")
    @ApiResponse(responseCode = "E06405", description = "S3에 파일을 업로드하는 중 오류가 발생했습니다.")
    void updateMemberProfileImageInfo(Member member, MultipartFile multipartFile);

    @Operation(summary = "내 일반 정보 수정", description = "로그인 한 사용자의 일반 정보(닉네임, 백준 id)를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "일반 정보 수정 성공")
    void updateMemberGeneralInfo(Member member,
            MemberGeneralUpdateRequest memberGeneralUpdateRequest);

    @Operation(summary = "내 비밀번호 수정", description = "로그인 한 사용자의 비밀번호를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "비밀번호 수정 성공")
    @ApiResponse(responseCode = "E01201", description = "비밀번호가 일치하지 않습니다.")
    void updateMemberPassword(Member member,
            MemberPasswordUpdateRequest memberPasswordUpdateRequest);
}
