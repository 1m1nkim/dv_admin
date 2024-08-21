package deepvue.admin.app.domain.repository.all;

import com.querydsl.jpa.impl.JPAQueryFactory;
import deepvue.admin.app.domain.entity.monitoring.BatchJobInstanceEntity;
import deepvue.admin.app.domain.entity.monitoring.QBatchJobInstanceEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class BatchJobInstanceAllRepositoryImpl extends QuerydslRepositorySupport implements BatchJobInstanceAllRepository {

    private final JPAQueryFactory queryFactory;
    private final QBatchJobInstanceEntity $BatchJobInstance = QBatchJobInstanceEntity.batchJobInstanceEntity;

    public BatchJobInstanceAllRepositoryImpl(JPAQueryFactory queryFactory) {
        super(BatchJobInstanceEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<BatchJobInstanceEntity> findAllInstances() {
        return queryFactory.selectFrom($BatchJobInstance)
            .fetch();
    }
}
