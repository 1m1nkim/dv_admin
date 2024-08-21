package deepvue.admin.app.domain.repository.all;

import deepvue.admin.app.domain.dto.monitoring.BatchJobExecutionDto;

import java.util.List;

public interface BatchJobExecutionAllRepository {
    List<BatchJobExecutionDto> findAllExecutions(String sortOrder) throws Exception;
}
