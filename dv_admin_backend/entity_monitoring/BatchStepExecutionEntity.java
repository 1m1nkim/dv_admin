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
@Table(name="BATCH_STEP_EXECUTION")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BatchStepExecutionEntity {   
   
  @Id
  @Column(name="Step_Execution_Id")
  private Long stepExecutionId;

  @Column(name="Version")
  private String version;

  @Column(name="Step_Name")
  private String stepName;

  @Column(name="Job_Execution_Id")
  private Long jobExecutionId;

  @Column(name="Start_Time")
  private java.sql.Timestamp startTime;

  @Column(name="End_Time")
  private java.sql.Timestamp endTime;

  @Column(name="Status")
  private String status;

  @Column(name="Commit_Count")
  private Long commitCount;

  @Column(name="Read_Count")
  private Long readCount;

  @Column(name="Filter_Count")
  private Long filterCount;

  @Column(name="Write_Count")
  private Long writeCount;

  @Column(name="Read_Skip_Count")
  private Long readSkipCount;

  @Column(name="Write_Skip_Count")
  private Long writeSkipCount;

  @Column(name="Process_Skip_Count")
  private Long processSkipCount;

  @Column(name="Rollback_Count")
  private Long rollbackCount;

  @Column(name="Exit_Code")
  private String exitCode;

  @Column(name="Exit_Message")
  private String exitMessage;
}
