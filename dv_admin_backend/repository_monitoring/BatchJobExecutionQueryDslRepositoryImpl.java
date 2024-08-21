package deepvue.admin.app.domain.repository.monitoring;

import com.querydsl.jpa.impl.JPAQueryFactory;

import deepvue.admin.app.domain.dto.monitoring.BatchJobExecutionDto;
import deepvue.admin.app.domain.entity.monitoring.BatchJobExecutionEntity;
import deepvue.admin.app.domain.entity.monitoring.QBatchJobExecutionEntity;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class BatchJobExecutionQueryDslRepositoryImpl extends QuerydslRepositorySupport implements BatchJobExecutionQueryDslRepository {
    private final JPAQueryFactory queryFactory;
    private final QBatchJobExecutionEntity $BatchJobExecution = QBatchJobExecutionEntity.batchJobExecutionEntity;

    public BatchJobExecutionQueryDslRepositoryImpl(JPAQueryFactory queryFactory) {
        super(BatchJobExecutionEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<BatchJobExecutionDto> ExecutionCorperations(Long jobExecutionId) throws Exception {
        List<BatchJobExecutionEntity> entities = queryFactory.selectFrom($BatchJobExecution)
            .where($BatchJobExecution.jobExecutionId.eq(jobExecutionId))
            .fetch();
        return BatchJobExecutionDto.fromEntity(entities);
    }
}
