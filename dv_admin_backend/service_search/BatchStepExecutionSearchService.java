package deepvue.admin.app.domain.service.search;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deepvue.admin.app.domain.dto.monitoring.BatchStepExecutionDto;
import deepvue.admin.app.domain.repository.search.BatchStepExecutionSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchStepExecutionSearchService {
    private final BatchStepExecutionSearchRepositoryImpl dateRepository;

    @Transactional(readOnly = true)
    public List<BatchStepExecutionDto> findExecutions(Long stepExecutionId, Long jobExecutionId, String start, String end, String status, String stepName) {
        return dateRepository.findByMultipleConditions(stepExecutionId, jobExecutionId, start, end, status, stepName);
    }
}
