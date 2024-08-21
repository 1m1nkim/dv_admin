package deepvue.admin.app.domain.controller.search;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import deepvue.admin.app.domain.dto.monitoring.BatchJobExecutionDto;
import deepvue.admin.app.domain.service.search.BatchJobExecutionSearchService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/batch/execution")
@RequiredArgsConstructor
public class BatchJobExecutionSearchController {
    private final BatchJobExecutionSearchService executionService;

    @GetMapping("")
    public ResponseEntity<List<BatchJobExecutionDto>> getExecutions(
            @RequestParam(name = "jobExecutionId", required = false) Long jobExecutionId,
            @RequestParam(name = "jobInstanceId", required = false) Long jobInstanceId,
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "end", required = false) String end,
            @RequestParam(name = "status", required = false) String status) {
        
       List<BatchJobExecutionDto> executions = executionService.findJobExecutions(jobExecutionId, jobInstanceId, start, end, status);

        if (executions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(executions);
    }
}
