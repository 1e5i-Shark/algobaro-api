package ei.algobaroapi.domain.problem.controller;

import ei.algobaroapi.domain.problem.dto.request.ProblemFindRequest;
import ei.algobaroapi.domain.problem.dto.response.ProblemHtmlResponse;
import ei.algobaroapi.domain.problem.service.BojProblemServiceImpl;
import ei.algobaroapi.global.config.swaggerdoc.ProblemControllerDoc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProblemController implements ProblemControllerDoc {

    private final BojProblemServiceImpl bojProblemService;

    @Override
    @GetMapping("/problems/html")
    public ProblemHtmlResponse getProblemHtml(@RequestBody @Valid ProblemFindRequest request) {
        return bojProblemService.getProblemInfoHtml(request);
    }
}
