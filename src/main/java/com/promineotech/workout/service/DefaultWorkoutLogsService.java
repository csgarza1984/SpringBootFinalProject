package com.promineotech.workout.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.workout.dao.WorkoutLogsDao;
import com.promineotech.workout.entity.Exercise;
import com.promineotech.workout.entity.ExerciseCategory;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultWorkoutLogsService implements WorkoutLogsService {
  
  @Autowired
  private WorkoutLogsDao workoutLogsDao;

  @Transactional(readOnly = true)
  public List<Exercise> fetchExercises(ExerciseCategory category) {
    log.info("The fetchExercises method was called with category={}", category);
    
    List<Exercise> exercises =  workoutLogsDao.fetchExercises(category);
    
    if(exercises.isEmpty()) {
      String msg = String.format("No exercises found with category=%s", 
          category); 
      throw new NoSuchElementException(msg);
    }
    
    Collections.sort(exercises);
    return exercises;
  } // fetchExercises

//  @Override
//  public List<Exercise> fetchAllExercises() {
//    log.info("The fetchExercises method was called with no category specified");
//    
//    List<Exercise> exercises =  workoutLogsDao.fetchAllExercises();
//    
//    if(exercises.isEmpty()) {
//      String msg = "No exercises found."; 
//      throw new NoSuchElementException(msg);
//    }
//    
//    Collections.sort(exercises);
//    return exercises;
//  }

} // class
