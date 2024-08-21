package deepvue.admin.app.domain.entity.monitoring;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name="BATCH_JOB_INSTANCE")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BatchJobInstanceEntity {   
   
  @Id
  @Column(name="JOB_INSTANCE_ID")
  private Long jobInstanceId;

  @Column(name="VERSION")
  private String version;

  @Column(name="JOB_NAME")
  private String jobName;

  @Column(name="JOB_KEY")
  private String jobKey;
}
