package deepvue.admin.app.domain.service.search;

import deepvue.admin.app.domain.dto.monitoring.BatchStepExecutionDto;
import deepvue.admin.app.domain.repository.search.ExecutionStepSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExecutionStepSearchService {
    private final ExecutionStepSearchRepositoryImpl repository;

    @Transactional(readOnly = true)
    public List<BatchStepExecutionDto> findStepExecutionsByJobExecutionId(Long stepExecutionId) {
        return repository.findByJobExecutionId(stepExecutionId);
    }
}
