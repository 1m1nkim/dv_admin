package deepvue.admin.app.domain.service.all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import deepvue.admin.app.domain.dto.monitoring.BatchJobExecutionDto;
import deepvue.admin.app.domain.repository.all.BatchJobExecutionAllRepository;

import java.util.List;

@Service
public class BatchJobExecutionAllService {

    @Autowired
    private BatchJobExecutionAllRepository batchJobExecutionAllRepository;

    public List<BatchJobExecutionDto> getExecutions(String sortOrder) throws Exception {
        return batchJobExecutionAllRepository.findAllExecutions(sortOrder);
    }
}
