package deepvue.admin.app.domain.repository.all;

import com.querydsl.core.types.OrderSpecifier;
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
public class BatchStepExecutionAllRepositoryImpl extends QuerydslRepositorySupport implements BatchStepExecutionAllRepository {

    private final JPAQueryFactory queryFactory;
    private final QBatchStepExecutionEntity $BatchStepExecution = QBatchStepExecutionEntity.batchStepExecutionEntity;

    public BatchStepExecutionAllRepositoryImpl(JPAQueryFactory queryFactory) {
        super(BatchStepExecutionEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<BatchStepExecutionDto> findAllExecutions(String sortOrder) {
        OrderSpecifier<?> orderSpecifier = "ASC".equalsIgnoreCase(sortOrder) ? $BatchStepExecution.startTime.asc() : $BatchStepExecution.startTime.desc();

        List<BatchStepExecutionEntity> entities = queryFactory.selectFrom($BatchStepExecution)
            .orderBy(orderSpecifier)
            .fetch();

        return BatchStepExecutionDto.fromEntity(entities);
    }
    
}
