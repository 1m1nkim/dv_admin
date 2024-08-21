package deepvue.admin.app.domain.service.monitoring;

import java.util.Optional;

import org.springframework.stereotype.Service;

import deepvue.admin.app.domain.dto.monitoring.BatchJobInstanceDto;
import deepvue.admin.app.domain.repository.monitoring.BatchJobInstanceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchJobInstanceService {
    private final BatchJobInstanceRepository repository;

    public Optional<BatchJobInstanceDto> batchJobInstanceDtoService(Long jobInstanceId){

        BatchJobInstanceDto dto = BatchJobInstanceDto.of(repository.findByjobInstanceId(jobInstanceId));

        return Optional.ofNullable(dto);
    }
    
}
