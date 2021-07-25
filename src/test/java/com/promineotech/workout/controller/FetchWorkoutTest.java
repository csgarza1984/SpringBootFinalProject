package com.promineotech.workout.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.promineotech.workout.controller.support.FetchWorkoutTestSupport;
import com.promineotech.workout.entity.ExerciseCategory;
import com.promineotech.workout.entity.Workout;
import com.promineotech.workout.entity.WorkoutTarget;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FetchWorkoutTest extends FetchWorkoutTestSupport {

  @Test
  void testThatExercisesAreReturnedWhenAValidTargetAreaIsSupplied() {
    // Given:  a valid targetArea and URI
    WorkoutTarget target = WorkoutTarget.CHEST;
    String uri = String.format("%s?target=%s", getBaseUri(), target);
    
    //System.out.println(uri);
    
    // When:  a connection is made to the URI
    ResponseEntity<Workout> response = getRestTemplate().getForEntity(uri, Workout.class);
    
    //Then:  a status code of OK (200) is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

  } // testThatExercisesAreReturnedWhenAValidtargetAreaIsSupplied method

  @Test
  void testThatExercisesAreReturnedWhenAValidCategoryIsSupplied() {
    // Given:  a valid category and URI
    ExerciseCategory category = ExerciseCategory.CARDIO;
    String uri = String.format("%s?target=%s", getBaseUri(), category);
    
    //System.out.println(uri);
    
    // When:  a connection is made to the URI
    ResponseEntity<Workout> response = getRestTemplate().getForEntity(uri, Workout.class);
    
    //Then:  a status code of OK (200) is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

  } // testThatExercisesAreReturnedWhenAValidCategoryIsS method

  
  
} // FetchWorkoutTest class
