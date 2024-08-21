package deepvue.admin.app.domain.repository.all;

import deepvue.admin.app.domain.entity.monitoring.BatchJobInstanceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchJobInstanceAllRepository {
    List<BatchJobInstanceEntity> findAllInstances();
}
