package deepvue.admin.app.domain.service.search;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deepvue.admin.app.domain.dto.monitoring.BatchJobExecutionDto;
import deepvue.admin.app.domain.repository.search.BatchJobExecutionSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchJobExecutionSearchService {
    private final BatchJobExecutionSearchRepositoryImpl dateRepository;

    @Transactional(readOnly = true)
    public List<BatchJobExecutionDto> findJobExecutions(Long jobExecutionId, Long jobInstanceId, String start, String end, String status) {
        return dateRepository.findByJobMultipleConditions(jobExecutionId, jobInstanceId, start, end, status);
    }
}