package deepvue.admin.app.domain.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import deepvue.admin.app.domain.dto.monitoring.BatchStepExecutionDto;
import deepvue.admin.app.domain.entity.monitoring.BatchStepExecutionEntity;
import deepvue.admin.app.domain.entity.monitoring.QBatchStepExecutionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class BatchStepExecutionSearchRepositoryImpl extends QuerydslRepositorySupport implements BatchStepExecutionSearchRepository {
    private final JPAQueryFactory queryFactory;
    private final QBatchStepExecutionEntity $BatchStepExecution = QBatchStepExecutionEntity.batchStepExecutionEntity;

    public BatchStepExecutionSearchRepositoryImpl(JPAQueryFactory queryFactory) {
        super(BatchStepExecutionEntity.class);
        this.queryFactory = queryFactory;
    }

    public List<BatchStepExecutionDto> findByMultipleConditions(Long stepExecutionId, Long jobExecutionId, String startFormatted, String endFormatted, String status, String stepName) {
        BooleanBuilder builder = new BooleanBuilder();

        if (stepExecutionId != null) {
            builder.and($BatchStepExecution.stepExecutionId.eq(stepExecutionId));
        }

        if (jobExecutionId != null) {
            builder.and($BatchStepExecution.jobExecutionId.eq(jobExecutionId));
        }

        if (startFormatted != null && !startFormatted.isEmpty()) {
            builder.and(Expressions.stringTemplate("DATE_FORMAT({0}, '%y%m%d')", $BatchStepExecution.startTime).between(startFormatted, endFormatted != null ? endFormatted : startFormatted));
        }

        if (status != null && !status.isEmpty()) {
            builder.and($BatchStepExecution.status.eq(status));
        }

        if (stepName != null && !stepName.isEmpty()) {
            builder.and($BatchStepExecution.stepName.eq(stepName));
        }

        List<BatchStepExecutionEntity> entities = queryFactory.selectFrom($BatchStepExecution)
            .where(builder)
            .orderBy($BatchStepExecution.startTime.desc())
            .fetch();

        return BatchStepExecutionDto.fromEntity(entities);
    }
}
