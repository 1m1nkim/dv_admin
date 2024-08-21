package deepvue.admin.app.domain.entity.monitoring;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBatchStepExecutionEntity is a Querydsl query type for BatchStepExecutionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBatchStepExecutionEntity extends EntityPathBase<BatchStepExecutionEntity> {

    private static final long serialVersionUID = -613615046L;

    public static final QBatchStepExecutionEntity batchStepExecutionEntity = new QBatchStepExecutionEntity("batchStepExecutionEntity");

    public final NumberPath<Long> commitCount = createNumber("commitCount", Long.class);

    public final DateTimePath<java.sql.Timestamp> endTime = createDateTime("endTime", java.sql.Timestamp.class);

    public final StringPath exitCode = createString("exitCode");

    public final StringPath exitMessage = createString("exitMessage");

    public final NumberPath<Long> filterCount = createNumber("filterCount", Long.class);

    public final NumberPath<Long> jobExecutionId = createNumber("jobExecutionId", Long.class);

    public final NumberPath<Long> processSkipCount = createNumber("processSkipCount", Long.class);

    public final NumberPath<Long> readCount = createNumber("readCount", Long.class);

    public final NumberPath<Long> readSkipCount = createNumber("readSkipCount", Long.class);

    public final NumberPath<Long> rollbackCount = createNumber("rollbackCount", Long.class);

    public final DateTimePath<java.sql.Timestamp> startTime = createDateTime("startTime", java.sql.Timestamp.class);

    public final StringPath status = createString("status");

    public final NumberPath<Long> stepExecutionId = createNumber("stepExecutionId", Long.class);

    public final StringPath stepName = createString("stepName");

    public final StringPath version = createString("version");

    public final NumberPath<Long> writeCount = createNumber("writeCount", Long.class);

    public final NumberPath<Long> writeSkipCount = createNumber("writeSkipCount", Long.class);

    public QBatchStepExecutionEntity(String variable) {
        super(BatchStepExecutionEntity.class, forVariable(variable));
    }

    public QBatchStepExecutionEntity(Path<? extends BatchStepExecutionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBatchStepExecutionEntity(PathMetadata metadata) {
        super(BatchStepExecutionEntity.class, metadata);
    }

}

