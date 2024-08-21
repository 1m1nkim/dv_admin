package deepvue.admin.app.domain.repository.monitoring;

import com.querydsl.jpa.impl.JPAQueryFactory;

import deepvue.admin.app.domain.dto.monitoring.BatchJobInstanceDto;
import deepvue.admin.app.domain.entity.monitoring.BatchJobInstanceEntity;
import deepvue.admin.app.domain.entity.monitoring.QBatchJobInstanceEntity;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class BatchJobInstanceQueryDslRepositoryImpl extends QuerydslRepositorySupport implements BatchJobInstanceQueryDslRepository {
    private final JPAQueryFactory queryFactory;
    private final QBatchJobInstanceEntity $BatchJobInstance = QBatchJobInstanceEntity.batchJobInstanceEntity;

    public BatchJobInstanceQueryDslRepositoryImpl(JPAQueryFactory queryFactory) {
        super(BatchJobInstanceEntity.class);
        this.queryFactory = queryFactory;
    }

    
    @Override
    public List<BatchJobInstanceDto> instanceCorperations(Long jobInstanceId) throws Exception {
        List<BatchJobInstanceEntity> entities = queryFactory.selectFrom($BatchJobInstance)
            .where($BatchJobInstance.jobInstanceId.eq(jobInstanceId))
            .fetch();
            return BatchJobInstanceDto.fromEntity(entities);
    }
}
