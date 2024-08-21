package deepvue.admin.app.domain.service.monitoring;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deepvue.admin.app.domain.dto.monitoring.BatchStepExecutionDto;
import deepvue.admin.app.domain.repository.monitoring.BatchStepExecutionQueryDslRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchStepExecutionService {
    private final BatchStepExecutionQueryDslRepository queryDslRepository;

    @Transactional(readOnly = true)
    public List<BatchStepExecutionDto> batchStepExecutionDtoServiceQueryDsl(Long id) throws Exception {
        List<BatchStepExecutionDto> batchStepExecutionDtos = queryDslRepository.StepExecutionCorperations(id);
        return batchStepExecutionDtos;
    }

    @Transactional(readOnly = true)
    public List<BatchStepExecutionDto> batchStepExecutionDtoServiceQueryDsl2(Long id) throws Exception {
        return queryDslRepository.StepExecutionCorperations(id);
    }

}
