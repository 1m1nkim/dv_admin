package deepvue.admin.app.domain.entity.monitoring;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBatchJobInstanceEntity is a Querydsl query type for BatchJobInstanceEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBatchJobInstanceEntity extends EntityPathBase<BatchJobInstanceEntity> {

    private static final long serialVersionUID = -1479183808L;

    public static final QBatchJobInstanceEntity batchJobInstanceEntity = new QBatchJobInstanceEntity("batchJobInstanceEntity");

    public final NumberPath<Long> jobInstanceId = createNumber("jobInstanceId", Long.class);

    public final StringPath jobKey = createString("jobKey");

    public final StringPath jobName = createString("jobName");

    public final StringPath version = createString("version");

    public QBatchJobInstanceEntity(String variable) {
        super(BatchJobInstanceEntity.class, forVariable(variable));
    }

    public QBatchJobInstanceEntity(Path<? extends BatchJobInstanceEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBatchJobInstanceEntity(PathMetadata metadata) {
        super(BatchJobInstanceEntity.class, metadata);
    }

}

