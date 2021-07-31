package com.promineotech.workout.dao;

import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import com.promineotech.workout.entity.Exercise;
import com.promineotech.workout.entity.ExerciseDetails;
import com.promineotech.workout.entity.Person;
import com.promineotech.workout.entity.Workout;

public interface WorkoutLogDao {
  
  Optional<Person> fetchPerson(String person);

  Optional<String> fetchWorkoutDate(String string);

  Workout saveWorkout(Person person, String workoutDate, 
      List<Exercise> exercises, List<ExerciseDetails> exerciseDetails);

  List<Exercise> fetchExercises(List<String> exerciseIds);

  List<ExerciseDetails> fetchExerciseDetails(
      List<String> exerciseDetails);
  
  void deleteWorkout(Long person_fk, String workoutDate);

}
