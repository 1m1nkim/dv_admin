package deepvue.admin.app.domain.controller.search;

import deepvue.admin.app.domain.dto.monitoring.BatchJobExecutionDto;
import deepvue.admin.app.domain.service.search.InstanceExecutionSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/batch/instance/execution")
@RequiredArgsConstructor
public class InstanceExecutionSearchController {
    private final InstanceExecutionSearchService service;

    @GetMapping("")
    public ResponseEntity<List<BatchJobExecutionDto>> getExecutionsByInstanceId(@RequestParam(name = "jobInstanceId") Long jobInstanceId) {
        List<BatchJobExecutionDto> executions = service.findExecutionsByInstanceId(jobInstanceId);
        if (executions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(executions);
    }
}