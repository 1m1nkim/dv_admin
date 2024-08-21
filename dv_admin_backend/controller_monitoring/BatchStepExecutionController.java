package deepvue.admin.app.domain.controller.monitoring;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import deepvue.admin.app.domain.dto.monitoring.BatchStepExecutionDto;
import deepvue.admin.app.domain.service.all.BatchStepExecutionAllService;
import deepvue.admin.app.domain.service.monitoring.BatchStepExecutionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/batch/step/")
@RequiredArgsConstructor
public class BatchStepExecutionController {

    private final BatchStepExecutionService bseService;
    private final BatchStepExecutionAllService bseAService;

    @GetMapping("monitoring")
    public ResponseEntity<List<BatchStepExecutionDto>> getBatchStepExecutionQueryDsl1(@RequestParam(name = "stepExecutionId", required = true) Long stepExecutionId) throws Exception {
        List<BatchStepExecutionDto> batchStepExecutionDtos = bseService.batchStepExecutionDtoServiceQueryDsl(stepExecutionId);
        if (batchStepExecutionDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(batchStepExecutionDtos);
    }

    @GetMapping("all")
    public List<BatchStepExecutionDto> getExecutions(@RequestParam(defaultValue = "DESC") String sort) throws Exception {
        return bseAService.findAllExecutions(sort);
    }

}
