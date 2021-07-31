package com.promineotech.workout.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class DeleteRequest {
  
  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[A-Z_]*")
   private String personId;
  
  @NotNull
  @Pattern(regexp = "\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])")
  private String workoutDate;

}
