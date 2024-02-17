package ei.algobaroapi.domain.compile.service;

import ei.algobaroapi.domain.compile.dto.request.CompileExecutionRequest;
import ei.algobaroapi.domain.compile.dto.response.CompileExecutionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JdoodleCompileServiceImpl implements CompileService {

    @Override
    public CompileExecutionResponse executeCode(CompileExecutionRequest request) {
        return null;
    }
}
