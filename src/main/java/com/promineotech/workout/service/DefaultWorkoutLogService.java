package com.promineotech.workout.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.workout.dao.WorkoutLogDao;
import com.promineotech.workout.entity.Exercise;
import com.promineotech.workout.entity.ExerciseDetails;
import com.promineotech.workout.entity.Person;
import com.promineotech.workout.entity.Workout;
import com.promineotech.workout.entity.WorkoutRequest;

@Service
public class DefaultWorkoutLogService implements WorkoutLogService {
  
  @Autowired
  private WorkoutLogDao workoutLogDao;
  
  @Transactional
  @Override
  public Workout createWorkoutLog(WorkoutRequest workoutRequest) {
//    Person person = workoutLogDao.fetchPerson(workoutRequest.getPerson());
    Person person = getPerson(workoutRequest);
    String workoutDate = getWorkoutDate(workoutRequest);
    List<Exercise> exercises = getExercise(workoutRequest);
    List<ExerciseDetails> exerciseDetails = getExerciseDetails(workoutRequest);
      
    return workoutLogDao.saveWorkout(person, workoutDate, exercises, exerciseDetails);
  }

  /**
   * 
   * @param workoutRequest
   * @return
   */
  private List<ExerciseDetails> getExerciseDetails(WorkoutRequest workoutRequest) {
    return workoutLogDao.fetchExerciseDetails(workoutRequest.getExerciseDetails());
  }

  /*
   * 
   */
  private List<Exercise> getExercise(WorkoutRequest workoutRequest) {
    return workoutLogDao.fetchExercises(workoutRequest.getExercises());
  }

  /**
   * 
   * @param workoutRequest
   * @return
   */
  private String getWorkoutDate(WorkoutRequest workoutRequest) {
    return workoutLogDao.fetchWorkoutDate(workoutRequest.getWorkoutDate())
        .orElseThrow(() -> new NoSuchElementException(
            "Invalid Date" + workoutRequest.getWorkoutDate() + " was entered."));
  }

  /**
   * 
   * @param workoutRequest
   * @return
   */
  private Person getPerson(WorkoutRequest workoutRequest) {
    return workoutLogDao.fetchPerson(workoutRequest.getPerson())
        .orElseThrow(() -> new NoSuchElementException(
            "Person with ID=" + workoutRequest.getPerson() + " was not found."));
  }


} // DefaultWorkoutLogService class
