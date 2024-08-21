package deepvue.admin.app.domain.repository.monitoring;

import java.util.List;

import deepvue.admin.app.domain.dto.monitoring.BatchStepExecutionDto;

// import org.springframework.stereotype.Repository;

// @Repository
public interface BatchStepExecutionQueryDslRepository {
    public List<BatchStepExecutionDto> StepExecutionCorperations(Long corpSeq) throws Exception;
}
