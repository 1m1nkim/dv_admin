package deepvue.admin.app.domain.service.all;

import deepvue.admin.app.domain.entity.monitoring.BatchJobInstanceEntity;
import deepvue.admin.app.domain.repository.all.BatchJobInstanceAllRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchJobInstanceAllService {
    private final BatchJobInstanceAllRepositoryImpl repository;

    @Transactional(readOnly = true)
    public List<BatchJobInstanceEntity> findAllInstances() {
        return repository.findAllInstances();
    }

}
