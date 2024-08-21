package deepvue.admin.app.domain.controller.search;

import deepvue.admin.app.domain.dto.monitoring.BatchStepExecutionDto;
import deepvue.admin.app.domain.repository.search.ExecutionStepSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/batch/execution/step")
@RequiredArgsConstructor
public class ExecutionStepSearchController {
    private final  ExecutionStepSearchRepositoryImpl service;

    @GetMapping("")
    public ResponseEntity<List<BatchStepExecutionDto>> getStepExecutionsByJobExecutionId(@RequestParam(name = "jobExecutionId") Long jobExecutionId) {
        List<BatchStepExecutionDto> stepExecutions = service.findByJobExecutionId(jobExecutionId);
        if (stepExecutions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stepExecutions);
    }
}