package com.promineotech.workout.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.workout.entity.Exercise;
import com.promineotech.workout.entity.ExerciseCategory;
import com.promineotech.workout.service.WorkoutLogsService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultWorkoutLogsController implements WorkoutLogsController {

  @Autowired
  private WorkoutLogsService workoutLogsService;
  
  public List<Exercise> fetchWorkouts(ExerciseCategory category) {
    log.debug("category={}", category);
    return workoutLogsService.fetchExercises(category);
  }
  
//  public List<Exercise> fetchWorkouts() {
//    log.debug("category=all");
//    return workoutLogsService.fetchAllExercises();
//  }
//  

}
