package deepvue.admin.app.domain.controller.monitoring;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import deepvue.admin.app.domain.dto.monitoring.BatchJobExecutionDto;
import deepvue.admin.app.domain.service.all.BatchJobExecutionAllService;
import deepvue.admin.app.domain.service.monitoring.BatchJobExecutionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/batch/execution/")
@RequiredArgsConstructor
public class BatchJobExecutionController {
    private final BatchJobExecutionService bjeService;
    private final BatchJobExecutionAllService bjeAService;

   


    @GetMapping("monitoring")
    public ResponseEntity<List<BatchJobExecutionDto>> getBatchJobExecutionQueryDsl1(@RequestParam(name = "jobExecutionId", required = true) Long jobExecutionId) throws Exception {
        List<BatchJobExecutionDto> batchJobExecutionDtos = bjeService.batchJobExecutionDtoServiceQueryDsl(jobExecutionId);
        if (batchJobExecutionDtos.isEmpty()) {
            log.debug("No data found for jobExecutionId: {}", jobExecutionId);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(batchJobExecutionDtos);
    }

    @GetMapping("all")
    public List<BatchJobExecutionDto> getExecutions(@RequestParam(defaultValue = "DESC") String sort) throws Exception {
        return bjeAService.getExecutions(sort);
    }
}
