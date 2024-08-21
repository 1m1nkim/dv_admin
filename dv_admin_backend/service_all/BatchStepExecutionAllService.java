package deepvue.admin.app.domain.service.all;

import deepvue.admin.app.domain.dto.monitoring.BatchStepExecutionDto;
import deepvue.admin.app.domain.repository.all.BatchStepExecutionAllRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchStepExecutionAllService {
    private final BatchStepExecutionAllRepositoryImpl repository;

    public List<BatchStepExecutionDto> findAllExecutions(String sortOrder) throws Exception {
        return repository.findAllExecutions(sortOrder);
    }

}
