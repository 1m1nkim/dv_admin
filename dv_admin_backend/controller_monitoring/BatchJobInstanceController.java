package deepvue.admin.app.domain.controller.monitoring;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import deepvue.admin.app.domain.dto.monitoring.BatchJobInstanceDto;
import deepvue.admin.app.domain.entity.monitoring.BatchJobInstanceEntity;
import deepvue.admin.app.domain.service.all.BatchJobInstanceAllService;
import deepvue.admin.app.domain.service.monitoring.BatchJobInstanceService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/batch/instance/")
@RequiredArgsConstructor
public class BatchJobInstanceController {

    private final BatchJobInstanceService bjiService;
    private final BatchJobInstanceAllService bjiAService;
    
    @GetMapping("monitoring")
    public ResponseEntity<BatchJobInstanceDto> getBatchJobInstance(@RequestParam(name = "BatchJobInstanceSeq", required = true) Long BatchJobInstanceSeq) throws Exception {
        return bjiService.batchJobInstanceDtoService(BatchJobInstanceSeq)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("all")
    public ResponseEntity<List<BatchJobInstanceEntity>> getAllInstances() {
        List<BatchJobInstanceEntity> instances = bjiAService.findAllInstances();
        return ResponseEntity.ok(instances);
    }
}