package deepvue.admin.app.domain.controller.search;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import deepvue.admin.app.domain.dto.monitoring.BatchStepExecutionDto;
import deepvue.admin.app.domain.service.search.BatchStepExecutionSearchService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/batch/step")
@RequiredArgsConstructor
public class BatchStepExecutionSearchController {
    private final BatchStepExecutionSearchService stepExecutionService;

    @GetMapping("")
    public ResponseEntity<List<BatchStepExecutionDto>> getStepExecutions(
            @RequestParam(name = "stepExecutionId", required = false) Long stepExecutionId,
            @RequestParam(name = "jobExecutionId", required = false) Long jobExecutionId,
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "end", required = false) String end,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "stepName", required = false) String stepName) {

        List<BatchStepExecutionDto> executions = stepExecutionService.findExecutions(stepExecutionId, jobExecutionId, start, end, status, stepName);

        if (executions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(executions);
    }
}
