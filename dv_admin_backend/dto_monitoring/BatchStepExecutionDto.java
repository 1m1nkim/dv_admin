package deepvue.admin.app.domain.dto.monitoring;

import deepvue.admin.app.domain.entity.monitoring.BatchStepExecutionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BatchStepExecutionDto {
    private Long stepExecutionId;
    private String version;
    private String stepName;
    private Long jobExecutionId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String status;
    private Long commitCount;
    private Long readCount;
    private Long filterCount;
    private Long writeCount;
    private Long readSkipCount;
    private Long writeSkipCount;
    private Long processSkipCount;
    private Long rollbackCount;
    private String exitCode;
    private String exitMessage;
    private Long duration;

    public static BatchStepExecutionDto of(BatchStepExecutionEntity entity) {
        return BatchStepExecutionDto.builder()
            .stepExecutionId(entity.getStepExecutionId())
            .version(entity.getVersion())
            .stepName(entity.getStepName())
            .jobExecutionId(entity.getJobExecutionId())
            .startTime(entity.getStartTime())
            .endTime(entity.getEndTime())
            .status(entity.getStatus())
            .commitCount(entity.getCommitCount())
            .readCount(entity.getReadCount())
            .filterCount(entity.getFilterCount())
            .writeCount(entity.getWriteCount())
            .readSkipCount(entity.getReadSkipCount())
            .writeSkipCount(entity.getWriteSkipCount())
            .processSkipCount(entity.getProcessSkipCount())
            .rollbackCount(entity.getRollbackCount())
            .exitCode(entity.getExitCode())
            .exitMessage(entity.getExitMessage())
            .duration(seDuration(entity.getStartTime(), entity.getEndTime()))
            .build();
    }

    public static List<BatchStepExecutionDto> fromEntity(List<BatchStepExecutionEntity> entities) {
        return entities.stream()
            .map(BatchStepExecutionDto::of)
            .toList();
    }
    private static Long seDuration(Timestamp startTime, Timestamp endTime) {
        if (startTime != null && endTime != null) {
            return (endTime.getTime() - startTime.getTime()) / 1000;
        }
        return null;
    }
}
