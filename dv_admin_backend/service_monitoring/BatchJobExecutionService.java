package deepvue.admin.app.domain.service.monitoring;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deepvue.admin.app.domain.dto.monitoring.BatchJobExecutionDto;
import deepvue.admin.app.domain.repository.monitoring.BatchJobExecutionQueryDslRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchJobExecutionService {
    private final BatchJobExecutionQueryDslRepository queryDslRepository;

    @Transactional(readOnly = true)
    public List<BatchJobExecutionDto> batchJobExecutionDtoServiceQueryDsl(Long id) throws Exception {
        List<BatchJobExecutionDto> batchJobExecutionDtos = queryDslRepository.ExecutionCorperations(id);
        return batchJobExecutionDtos;
    }

    @Transactional(readOnly = true)
    public List<BatchJobExecutionDto> batchJobExecutionDtoServiceQueryDsl2(Long id) throws Exception {
        return queryDslRepository.ExecutionCorperations(id);
    }

}
