package ei.algobaroapi.domain.compile.service;

import ei.algobaroapi.domain.compile.dto.request.CompileExecutionRequest;
import ei.algobaroapi.domain.compile.dto.request.JdoodleCompileRequest;
import ei.algobaroapi.domain.compile.dto.response.CompileExecutionResponse;
import ei.algobaroapi.domain.compile.dto.response.JdoodleCompileResponse;
import ei.algobaroapi.domain.compile.exception.JdoodleException;
import ei.algobaroapi.domain.compile.exception.common.CompileErrorCode;
import ei.algobaroapi.global.util.HttpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JdoodleCompileServiceImpl implements CompileService {

    private final HttpUtil httpUtil;
    @Value("${spring.myapp.jdoodle.client-id}")
    private String clientId;
    @Value("${spring.myapp.jdoodle.client-secret}")
    private String clientSecret;
    @Value("${spring.myapp.jdoodle.api-url}")
    private String jdoodleApiUrl;

    @Override
    public CompileExecutionResponse executeCode(CompileExecutionRequest request) {
        try {
            JdoodleCompileResponse jdoodleCompileResponse = sendJdoodleCompileRequest(
                    JdoodleCompileRequest.builder()
                            .clientId(clientId)
                            .clientSecret(clientSecret)
                            .stdin(request.getInput())
                            .script(request.getCode())
                            .language(request.getLanguage())
                            .build()
            );

            if (jdoodleCompileResponse.isCompileSuccess()) {
                return CompileExecutionResponse.success(jdoodleCompileResponse.getOutput());
            } else {
                return CompileExecutionResponse.failure();
            }

        } catch (Exception e) {
            throw JdoodleException.of(CompileErrorCode.JDOODLE_API_EXCEPTION);
        }
    }

    private JdoodleCompileResponse sendJdoodleCompileRequest(JdoodleCompileRequest request) {
        return httpUtil.requestPostWithBasicAuthorization(
                jdoodleApiUrl,
                request,
                JdoodleCompileResponse.class
        );
    }
}
