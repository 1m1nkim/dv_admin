package deepvue.admin.app.domain.repository.monitoring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deepvue.admin.app.domain.entity.monitoring.BatchJobInstanceEntity;

@Repository
public interface BatchJobInstanceRepository extends JpaRepository<BatchJobInstanceEntity, Long> {
    public BatchJobInstanceEntity findByjobInstanceId(Long jobInstanceId);    
}
