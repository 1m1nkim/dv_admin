package deepvue.admin.app.domain.repository.all;

import java.util.List;

import deepvue.admin.app.domain.dto.monitoring.BatchStepExecutionDto;

// import org.springframework.stereotype.Repository;

// @Repository
public interface BatchStepExecutionAllRepository {    
    List<BatchStepExecutionDto> findAllExecutions(String sortOrder) throws Exception;

}
