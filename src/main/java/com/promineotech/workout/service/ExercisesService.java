package com.promineotech.workout.service;

import java.util.List;
import com.promineotech.workout.entity.Exercise;
import com.promineotech.workout.entity.ExerciseCategory;

public interface ExercisesService {

  /**
   * 
   * @param category
   * @return
   */
  List<Exercise> fetchExercises(ExerciseCategory category);

  //List<Exercise> fetchAllExercises();

}
