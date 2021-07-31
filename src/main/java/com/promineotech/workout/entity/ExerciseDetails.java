package com.promineotech.workout.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExerciseDetails {
  private Long setPK;
  private Long exerciseFK;
  private int numReps;
  private int weight;
  private int durationInMinutes;
  private float distance;
  
  @JsonIgnore
  public Long getSetPK() {
    return setPK;
  }

}
