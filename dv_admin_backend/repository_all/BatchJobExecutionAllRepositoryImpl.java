package deepvue.admin.app.domain.repository.all;

import com.querydsl.core.types.OrderSpecifier;
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
public class BatchJobExecutionAllRepositoryImpl extends QuerydslRepositorySupport implements BatchJobExecutionAllRepository {

    private final JPAQueryFactory queryFactory;
    private final QBatchJobExecutionEntity $BatchJobExecution = QBatchJobExecutionEntity.batchJobExecutionEntity;

    public BatchJobExecutionAllRepositoryImpl(JPAQueryFactory queryFactory) {
        super(BatchJobExecutionEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<BatchJobExecutionDto> findAllExecutions(String sortOrder) {
        OrderSpecifier<?> orderSpecifier = "ASC".equalsIgnoreCase(sortOrder) ? $BatchJobExecution.startTime.asc() : $BatchJobExecution.startTime.desc();

        List<BatchJobExecutionEntity> entities = queryFactory.selectFrom($BatchJobExecution)
            .orderBy(orderSpecifier)
            .fetch();

        return BatchJobExecutionDto.fromEntity(entities);
    }
}
