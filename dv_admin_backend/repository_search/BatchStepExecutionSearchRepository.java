package deepvue.admin.app.domain.repository.search;

import java.util.List;

import deepvue.admin.app.domain.dto.monitoring.BatchStepExecutionDto;

// import org.springframework.stereotype.Repository;

// @Repository
public interface BatchStepExecutionSearchRepository {
    public List<BatchStepExecutionDto> findByMultipleConditions(Long stepExecutionId, Long jobExecutionId, String startFormatted, String endFormatted, String status, String stepName) throws Exception;
}
