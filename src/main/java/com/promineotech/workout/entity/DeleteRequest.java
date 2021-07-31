package com.promineotech.workout.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DeleteRequest {
  
  @NotNull
  @Pattern(regexp = "^(.+)@(.+)$")
  private String email;
  
  @NotNull
  @Pattern(regexp = "\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])")
  private String workoutDate;

}
