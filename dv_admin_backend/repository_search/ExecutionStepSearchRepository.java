package deepvue.admin.app.domain.repository.search;

import java.util.List;

import deepvue.admin.app.domain.dto.monitoring.BatchStepExecutionDto;

// import org.springframework.stereotype.Repository;

// @Repository
public interface ExecutionStepSearchRepository {
    public List<BatchStepExecutionDto> findByJobExecutionId(Long corpSeq) throws Exception;
}
