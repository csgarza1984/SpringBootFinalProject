package com.promineotech.workout.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.workout.entity.Workout;
import com.promineotech.workout.entity.WorkoutRequest;
import com.promineotech.workout.service.WorkoutLogService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultWorkoutLogController implements WorkoutLogController {

  @Autowired
  private WorkoutLogService workoutLogService;
  
  @Override
  public Workout createWorkoutLog(WorkoutRequest workoutRequest) {
    log.debug("Workout={}", workoutRequest);
    return workoutLogService.createWorkoutLog(workoutRequest);
  }

  @Override
  public Workout deleteWorkoutLog(@Valid WorkoutRequest workoutRequest) {
    log.debug("DeletedWorkout={}", workoutRequest);
    return workoutLogService.deleteWorkoutLog(workoutRequest);
  }

}
