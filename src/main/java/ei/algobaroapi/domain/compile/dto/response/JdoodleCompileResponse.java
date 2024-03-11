package ei.algobaroapi.domain.compile.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class JdoodleCompileResponse {

    private final String output;

    private final int statusCode;

    private final String memory;

    private final String cpuTime;

    private final String compilationStatus;

    private final String projectKey;

    public static JdoodleCompileResponse of(
            String output,
            int statusCode,
            String memory,
            String cpuTime,
            String compilationStatus,
            String projectKey
    ) {
        return new JdoodleCompileResponse(output, statusCode, memory, cpuTime, compilationStatus,
                projectKey);
    }
}
