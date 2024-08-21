package deepvue.admin.app.domain.dto.monitoring;

import deepvue.admin.app.domain.entity.monitoring.BatchJobExecutionEntity;
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
public class BatchJobExecutionDto {
    private Long jobExecutionId;
    private String version;
    private Long jobInstanceId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String status;
    private String exitCode;
    private String exitMessage;
    private Long duration;

    public static BatchJobExecutionDto of(BatchJobExecutionEntity entity) {
        return BatchJobExecutionDto.builder()
            .jobExecutionId(entity.getJobExecutionId())
            .version(entity.getVersion())
            .jobInstanceId(entity.getJobInstanceId())
            .startTime(entity.getStartTime())
            .endTime(entity.getEndTime())
            .status(entity.getStatus())
            .exitCode(entity.getExitCode())
            .exitMessage(entity.getExitMessage())
            .duration(seDuration(entity.getStartTime(), entity.getEndTime()))
            .build();
    }

    public static List<BatchJobExecutionDto> fromEntity(List<BatchJobExecutionEntity> entities) {
        return entities.stream()
            .map(BatchJobExecutionDto::of)
            .toList();
    }

    private static Long seDuration(Timestamp startTime, Timestamp endTime) {
        if (startTime != null && endTime != null) {
            return (endTime.getTime() - startTime.getTime()) / 1000;
        }
        return null;
    }
}
