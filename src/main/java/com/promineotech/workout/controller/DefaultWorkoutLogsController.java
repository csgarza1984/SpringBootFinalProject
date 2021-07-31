package com.promineotech.workout.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.workout.entity.Workout;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultWorkoutLogsController implements WorkoutLogsController {

  public List<Workout> fetchWorkouts(String category) {
    log.debug("category={}", category);
    return null;
  }

}
