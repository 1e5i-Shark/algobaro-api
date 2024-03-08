package ei.algobaroapi.global.dto;

import java.util.List;
import java.util.function.Function;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageResponse<T, R> {

    private final List<R> content;
    private final int totalPages;
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
