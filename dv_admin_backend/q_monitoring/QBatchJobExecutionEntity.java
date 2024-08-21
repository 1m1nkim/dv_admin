package deepvue.admin.app.domain.entity.monitoring;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBatchJobExecutionEntity is a Querydsl query type for BatchJobExecutionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBatchJobExecutionEntity extends EntityPathBase<BatchJobExecutionEntity> {

    private static final long serialVersionUID = 1186251955L;

    public static final QBatchJobExecutionEntity batchJobExecutionEntity = new QBatchJobExecutionEntity("batchJobExecutionEntity");

    public final DateTimePath<java.sql.Timestamp> endTime = createDateTime("endTime", java.sql.Timestamp.class);

    public final StringPath exitCode = createString("exitCode");

    public final StringPath exitMessage = createString("exitMessage");

    public final NumberPath<Long> jobExecutionId = createNumber("jobExecutionId", Long.class);

    public final NumberPath<Long> jobInstanceId = createNumber("jobInstanceId", Long.class);

    public final DateTimePath<java.sql.Timestamp> startTime = createDateTime("startTime", java.sql.Timestamp.class);

    public final StringPath status = createString("status");

    public final StringPath version = createString("version");

    public QBatchJobExecutionEntity(String variable) {
        super(BatchJobExecutionEntity.class, forVariable(variable));
    }

    public QBatchJobExecutionEntity(Path<? extends BatchJobExecutionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBatchJobExecutionEntity(PathMetadata metadata) {
        super(BatchJobExecutionEntity.class, metadata);
    }

}

