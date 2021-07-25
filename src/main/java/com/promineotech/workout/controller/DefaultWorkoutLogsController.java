package com.promineotech.workout.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.workout.entity.Workout;

@RestController
public class DefaultWorkoutLogsController implements WorkoutLogsController {

  public List<Workout> fetchWorkouts(String category) {
    return null;
  }

}
