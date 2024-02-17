package ei.algobaroapi.domain.compile.service;

import ei.algobaroapi.domain.compile.dto.request.CompileExecutionRequest;
import ei.algobaroapi.domain.compile.dto.response.CompileExecutionResponse;

public interface CompileService {

    CompileExecutionResponse executeCode(CompileExecutionRequest request);
}
