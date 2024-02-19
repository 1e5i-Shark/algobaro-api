package ei.algobaroapi.domain.compile.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JdoodleCompileRequest {

    private String clientId;
    private String clientSecret;
    private String stdin;
    private String script;
    private String language;
}
