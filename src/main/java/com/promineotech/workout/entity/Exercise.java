package com.promineotech.workout.entity;

import java.util.Comparator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exercise implements Comparable<Exercise> {
  private Long exercisePK;
  private String exerciseId;
  private ExerciseCategory category;
  private String exerciseName;
  private ExerciseTarget targetArea;
  
  @JsonIgnore
  public Long getExercisePK() {
    return exercisePK;
  }

  @Override
  public int compareTo(Exercise that) {
    // @formatter:off
    return Comparator
        .comparing(Exercise::getExerciseId)
        .thenComparing(Exercise::getCategory)
        .thenComparing(Exercise::getExerciseName)
        .compare(this, that);
    // @formatter:on    
  }
}
