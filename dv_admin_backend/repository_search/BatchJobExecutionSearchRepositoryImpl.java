package deepvue.admin.app.domain.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
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
public class BatchJobExecutionSearchRepositoryImpl extends QuerydslRepositorySupport implements BatchJobExecutionSearchRepository {
    private final JPAQueryFactory queryFactory;
    private final QBatchJobExecutionEntity $BatchJobExecution = QBatchJobExecutionEntity.batchJobExecutionEntity;

    public BatchJobExecutionSearchRepositoryImpl(JPAQueryFactory queryFactory) {
        super(BatchJobExecutionEntity.class);
        this.queryFactory = queryFactory;
    }

   
    public List<BatchJobExecutionDto> findByJobMultipleConditions(Long jobExecutionId, Long jobInstanceId, String start, String end, String status) {
        BooleanBuilder builder = new BooleanBuilder();

        if (jobExecutionId != null) {
            builder.and($BatchJobExecution.jobExecutionId.eq(jobExecutionId));
        }

        if (jobInstanceId != null) {
            builder.and($BatchJobExecution.jobInstanceId.eq(jobInstanceId));
        }

        if (start != null && !start.isEmpty()) {
            builder.and(Expressions.stringTemplate("DATE_FORMAT({0}, '%y%m%d')", $BatchJobExecution.startTime).between(start, end != null ? end : start));
        }

        if (status != null && !status.isEmpty()) {
            builder.and($BatchJobExecution.status.eq(status));
        }

        List<BatchJobExecutionEntity> entities = queryFactory.selectFrom($BatchJobExecution)
            .where(builder)
            .orderBy($BatchJobExecution.startTime.desc())
            .fetch();

        return BatchJobExecutionDto.fromEntity(entities);
    }
}
