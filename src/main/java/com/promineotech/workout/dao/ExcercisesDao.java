package com.promineotech.workout.dao;

import java.util.List;
import com.promineotech.workout.entity.Exercise;
import com.promineotech.workout.entity.ExerciseCategory;

public interface ExcercisesDao {

  /**
   * 
   * @param category
   * @return
   */
  List<Exercise> fetchExercises(ExerciseCategory category);

  //List<Exercise> fetchAllExercises();

}
