package ei.algobaroapi.global.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.function.Function;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Schema(description = "페이지네이션 응답")
public class PageResponse<T, R> {

    @Schema(description = "조회 결과 목록")
    private final List<R> content;
    @Schema(description = "총 페이지 수", example = "5")
    private final int totalPages;
    @Schema(description = "총 데이터 수", example = "59")
    private final long totalElements;

    private PageResponse(Page<T> pageResponse, Function<T, R> converter) {
        this.content = pageResponse.getContent().stream().map(converter).toList();
        this.totalPages = pageResponse.getTotalPages();
        this.totalElements = pageResponse.getTotalElements();
    }

    public static <T, R> PageResponse<T, R> of(Page<T> pageResponse, Function<T, R> converter) {
        return new PageResponse<>(pageResponse, converter);
    }
}
