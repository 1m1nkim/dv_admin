package deepvue.admin.app.domain.repository.monitoring;

import com.querydsl.jpa.impl.JPAQueryFactory;

import deepvue.admin.app.domain.dto.monitoring.BatchStepExecutionDto;
import deepvue.admin.app.domain.entity.monitoring.BatchStepExecutionEntity;
import deepvue.admin.app.domain.entity.monitoring.QBatchStepExecutionEntity;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class BatchStepExecutionQueryDslRepositoryImpl implements BatchStepExecutionQueryDslRepository {
    private final JPAQueryFactory queryFactory;
    private final QBatchStepExecutionEntity $BatchStepExecution = QBatchStepExecutionEntity.batchStepExecutionEntity;

    public BatchStepExecutionQueryDslRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<BatchStepExecutionDto> StepExecutionCorperations(Long stepExecutionId) throws Exception {
        List<BatchStepExecutionEntity> entities = queryFactory.selectFrom($BatchStepExecution)
            .where($BatchStepExecution.stepExecutionId.eq(stepExecutionId))
            .fetch();
            return BatchStepExecutionDto.fromEntity(entities);
    }
}
