package com.promineotech.workout.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import com.promineotech.workout.controller.support.FetchWorkoutTestSupport;
import com.promineotech.workout.entity.ExerciseCategory;
import com.promineotech.workout.entity.Workout;
import io.swagger.v3.oas.models.PathItem.HttpMethod;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class FetchWorkoutTest extends FetchWorkoutTestSupport {

  @Test
  void testThatExercisesAreReturnedWhenAValidCategoryIsSupplied() {
    // Given:  a valid category and URI
    ExerciseCategory category = ExerciseCategory.CARDIO;
    String uri = String.format("%s?category=%s", getBaseUri(), category);
    
    //System.out.println(uri);
    
    // When:  a connection is made to the URI
    ResponseEntity<List<Workout>> response = 
        getRestTemplate().exchange(uri, HttpMethod.GET, null, 
            new ParameterizedTypeReference<>() {});
    
    //Then:  a status code of OK (200) is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    
    // And:  the actual list is the same as the expected list
    List<Workout> expected = buildExpected();
    assertThat(response.getBody()).isEqualTo(expected);
    

  } // testThatExercisesAreReturnedWhenAValidCategoryIsS method

//@Test
//void testThatExercisesAreReturnedWhenAValidTargetAreaIsSupplied() {
//  // Given:  a valid targetArea and URI
//  WorkoutTarget target = WorkoutTarget.ARMS;
//  String uri = String.format("%s?target=%s", getBaseUri(), target);
//  
//  //System.out.println(uri);
//  
//  // When:  a connection is made to the URI
//  ResponseEntity<Workout> response = getRestTemplate().getForEntity(uri, Workout.class);
//  
//  //Then:  a status code of OK (200) is returned
//  assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//} // testThatExercisesAreReturnedWhenAValidtargetAreaIsSupplied method
  
  
} // FetchWorkoutTest class
