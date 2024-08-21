package deepvue.admin.app.domain.entity.monitoring;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@Entity
@Table(name="BATCH_JOB_EXECUTION")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BatchJobExecutionEntity {   
   
    @Id
    @Column(name="JOB_EXECUTION_ID")
    private Long jobExecutionId;

    @Column(name="VERSION")
    private String version;

    @Column(name="JOB_INSTANCE_ID")
    private Long jobInstanceId;

    @Column(name="START_TIME")
    private Timestamp startTime;
  
    @Column(name="END_TIME")
    private Timestamp endTime;

    @Column(name="STATUS")
    private String status;
  
    @Column(name="EXIT_CODE")
    private String exitCode;
    
    @Column(name="EXIT_MESSAGE")  
    private String exitMessage;
}
