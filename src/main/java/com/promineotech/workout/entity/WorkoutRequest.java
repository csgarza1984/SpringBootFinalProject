package com.promineotech.workout.entity;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class WorkoutRequest {
  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[A-Z_]*")
  private String person;
 
  @NotNull
  @Pattern(regexp = "\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])")
  private String workoutDate;
  
  private List<@NotNull @Length(max = 30) @Pattern(
      regexp = "[\\w\\s]*") String> exercises;
  
  private List<@NotNull @Length(max = 30) @Pattern(
      regexp = "[\\w\\s]*") String> exerciseDetails;
}
