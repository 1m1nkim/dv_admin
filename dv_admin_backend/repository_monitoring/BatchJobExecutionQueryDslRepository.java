package deepvue.admin.app.domain.repository.monitoring;

import java.util.List;

import deepvue.admin.app.domain.dto.monitoring.BatchJobExecutionDto;

// import org.springframework.stereotype.Repository;

// @Repository
public interface BatchJobExecutionQueryDslRepository {
    public List<BatchJobExecutionDto> ExecutionCorperations(Long corpSeq) throws Exception;
}
