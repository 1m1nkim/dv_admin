package deepvue.admin.app.domain.repository.search;

import java.util.List;

import deepvue.admin.app.domain.dto.monitoring.BatchJobExecutionDto;

// import org.springframework.stereotype.Repository;

// @Repository
public interface InstanceExecutionSearchRepository {
    public List<BatchJobExecutionDto> findByJobInstanceId(Long corpSeq) throws Exception;
}
