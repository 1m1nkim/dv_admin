package deepvue.admin.app.domain.repository.search;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import deepvue.admin.app.domain.dto.monitoring.BatchJobExecutionDto;
import deepvue.admin.app.domain.entity.monitoring.BatchJobExecutionEntity;
import deepvue.admin.app.domain.entity.monitoring.QBatchJobExecutionEntity;
import deepvue.admin.app.domain.entity.monitoring.QBatchJobInstanceEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class InstanceExecutionSearchRepositoryImpl extends QuerydslRepositorySupport implements InstanceExecutionSearchRepository {
    private final JPAQueryFactory queryFactory;
    private final QBatchJobExecutionEntity $BatchJobExecution = QBatchJobExecutionEntity.batchJobExecutionEntity;
    private final QBatchJobInstanceEntity $BatchJobInstance = QBatchJobInstanceEntity.batchJobInstanceEntity;

    public InstanceExecutionSearchRepositoryImpl(JPAQueryFactory queryFactory) {
        super(BatchJobExecutionEntity.class);
        this.queryFactory = queryFactory;
    }

    public List<BatchJobExecutionDto> findByJobInstanceId(Long jobInstanceId) {
        List<BatchJobExecutionEntity> entities = queryFactory.selectFrom($BatchJobExecution)
        .where($BatchJobExecution.jobInstanceId.eq(
            JPAExpressions.select($BatchJobInstance.jobInstanceId)
                .from($BatchJobInstance)
                .where($BatchJobInstance.jobInstanceId.eq(jobInstanceId))
        ))
        .orderBy($BatchJobExecution.startTime.desc())
        .fetch();

        return BatchJobExecutionDto.fromEntity(entities);
    }

}
