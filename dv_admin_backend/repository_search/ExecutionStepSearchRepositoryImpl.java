package deepvue.admin.app.domain.repository.search;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import deepvue.admin.app.domain.dto.monitoring.BatchStepExecutionDto;
import deepvue.admin.app.domain.entity.monitoring.BatchJobExecutionEntity;
import deepvue.admin.app.domain.entity.monitoring.BatchStepExecutionEntity;
import deepvue.admin.app.domain.entity.monitoring.QBatchJobExecutionEntity;
import deepvue.admin.app.domain.entity.monitoring.QBatchStepExecutionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ExecutionStepSearchRepositoryImpl extends QuerydslRepositorySupport implements ExecutionStepSearchRepository {
    private final JPAQueryFactory queryFactory;
    private final QBatchJobExecutionEntity $BatchJobExecution = QBatchJobExecutionEntity.batchJobExecutionEntity;
    private final QBatchStepExecutionEntity $BatchStepExecution = QBatchStepExecutionEntity.batchStepExecutionEntity;

    public ExecutionStepSearchRepositoryImpl(JPAQueryFactory queryFactory) {
        super(BatchJobExecutionEntity.class);
        this.queryFactory = queryFactory;
    }

    
    public List<BatchStepExecutionDto> findByJobExecutionId(Long jobExecutionId) {
        List<BatchStepExecutionEntity> entities = queryFactory.selectFrom($BatchStepExecution)
        .where($BatchStepExecution.jobExecutionId.eq(
            JPAExpressions.select($BatchJobExecution.jobExecutionId)
                .from($BatchJobExecution)
                .where($BatchJobExecution.jobExecutionId.eq(jobExecutionId))
        ))
        .orderBy($BatchStepExecution.startTime.desc())
        .fetch();

            return BatchStepExecutionDto.fromEntity(entities);
    }
}
