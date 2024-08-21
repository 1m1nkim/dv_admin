package deepvue.admin.app.domain.repository.search;

import java.util.List;

import deepvue.admin.app.domain.dto.monitoring.BatchJobExecutionDto;

// import org.springframework.stereotype.Repository;

// @Repository
public interface BatchJobExecutionSearchRepository {
    public List<BatchJobExecutionDto> findByJobMultipleConditions(Long jobExecutionId, Long jobInstanceId, String start, String end, String status) throws Exception;
}
