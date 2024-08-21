package deepvue.admin.app.domain.repository.monitoring;

import java.util.List;

import deepvue.admin.app.domain.dto.monitoring.BatchJobInstanceDto;

// import org.springframework.stereotype.Repository;

// @Repository
public interface BatchJobInstanceQueryDslRepository {
    public List<BatchJobInstanceDto> instanceCorperations(Long corpSeq) throws Exception;
}
