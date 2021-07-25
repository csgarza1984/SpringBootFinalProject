package com.promineotech.workout.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Workout {
  private Long exercisePK;
  private  String exerciseId;
  private ExerciseCategory category;
  private String exerciseName;
  private String targetArea;
}
