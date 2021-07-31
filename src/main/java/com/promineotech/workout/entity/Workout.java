package com.promineotech.workout.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Workout {
  private Long workoutPK;
  private Person person;
  private String workoutDate;
  private List<Exercise> exercises;

  @JsonIgnore
  public Long getWorkoutPK() {
    return workoutPK;
  }
}
