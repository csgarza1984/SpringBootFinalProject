package com.promineotech.workout.service;

import com.promineotech.workout.entity.Workout;
import com.promineotech.workout.entity.WorkoutRequest;

public interface WorkoutLogService {

  Workout createWorkoutLog(WorkoutRequest workoutRequest);

  Workout deleteWorkoutLog(WorkoutRequest workoutRequest);

}
