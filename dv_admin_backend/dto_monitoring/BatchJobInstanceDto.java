package deepvue.admin.app.domain.dto.monitoring;

import java.util.List;

import deepvue.admin.app.domain.entity.monitoring.BatchJobInstanceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BatchJobInstanceDto {
  private Long jobInstanceId;
  private String version;
  private String jobName;
  private String jobKey;

  public static BatchJobInstanceDto of(BatchJobInstanceEntity entity) {
    return BatchJobInstanceDto.builder()
        .jobInstanceId(entity.getJobInstanceId())
        .version(entity.getVersion())
        .jobName(entity.getJobName())
        .jobKey(entity.getJobKey())
        .build();
  }

  public static List<BatchJobInstanceDto> fromEntity(List<BatchJobInstanceEntity> entities) {
    return entities.stream()
        .map(BatchJobInstanceDto::of)
        .toList();
  }
}
