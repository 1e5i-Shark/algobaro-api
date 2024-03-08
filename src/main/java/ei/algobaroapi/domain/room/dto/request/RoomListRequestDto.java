package ei.algobaroapi.domain.room.dto.request;

import ei.algobaroapi.domain.room.domain.RoomAccessType;
import ei.algobaroapi.domain.room.domain.RoomStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "방 리스트 조회 요청 정보")
@Getter
@Builder
public class RoomListRequestDto {

    @Schema(description = "페이지 번호", example = "0")
    private Integer page;

    @Schema(description = "페이지 크기", example = "6")
    private Integer size;

    @Schema(description = "방 제목 검색어", example = "초보방")
    private String searchTitle;

    @Schema(description = "방 상태(입장 가능으로 사용 시 RECRUITING, 체크 해제 시 요청 x)", example = "RECRUITING")
    private RoomStatus roomStatus;

    @Schema(description = "방 접근 타입(비밀방 사용 시 PRIVATE, 체크 해제 시 요청 x)", example = "PRIVATE")
    private RoomAccessType roomAccessType;

    @Schema(description = "언어 필터링", example = "JAVA,PYTHON")
    private List<String> languages;
}
