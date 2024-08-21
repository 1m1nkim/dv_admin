package deepvue.admin.app.domain.service.search;

import deepvue.admin.app.domain.dto.monitoring.BatchJobExecutionDto;
import deepvue.admin.app.domain.repository.search.InstanceExecutionSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstanceExecutionSearchService {
    private final InstanceExecutionSearchRepositoryImpl repository;

    @Transactional(readOnly = true)
    public List<BatchJobExecutionDto> findExecutionsByInstanceId(Long jobInstanceId) {
        return repository.findByJobInstanceId(jobInstanceId);
    }
}
