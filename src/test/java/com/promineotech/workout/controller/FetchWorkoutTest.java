package com.promineotech.workout.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.workout.controller.support.FetchWorkoutTestSupport;
import com.promineotech.workout.entity.Exercise;
import com.promineotech.workout.entity.ExerciseCategory;
import com.promineotech.workout.service.ExercisesService;

class FetchWorkoutTest {
  
  @Nested
  @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
  @ActiveProfiles("test")
  @Sql(
      scripts = {"classpath:flyway/migrations/WorkoutLogsSchema.sql",
      "classpath:flyway/migrations/WorkoutLogData.sql"},
      config = @SqlConfig(encoding = "utf-8"))  
  class TestsThatDoNotPolluteTheApplicationContext extends FetchWorkoutTestSupport {

    /**
     * 
     */
    @Test
    void testThatExercisesAreReturnedWhenAValidCategoryIsSupplied() {
      // Given:  a valid category and URI
      ExerciseCategory category = ExerciseCategory.STRENGTH;
      String uri = String.format("%s?category=%s", getBaseUriForExercises(), category);
      
      //System.out.println(uri);
      
      // When:  a connection is made to the URI
      ResponseEntity<List<Exercise>> response = 
          getRestTemplate().exchange(uri, HttpMethod.GET, null, 
              new ParameterizedTypeReference<List<Exercise>>() {});
          
      //Then:  a status code of OK (200) is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
      
      // And:  the actual list is the same as the expected list
      List<Exercise> actual = response.getBody();
      List<Exercise> expected = buildExpected();
      
      System.out.println(expected);
      assertThat(actual).isEqualTo(expected);      
    } // testThatExercisesAreReturnedWhenAValidCategoryIsSupplied method

    /**
     * 
     */
    @Test
    void testThatAnErrorMessageIsReturnedWhenAnUnknownExerciseNameIsSupplied() {
      // Given:  a valid exercise name and URI
      String exerciseName = "Unknown Value";
      String uri = String.format("%s?exercise_name=%s", getBaseUriForExercises(), exerciseName);
      
      //System.out.println(uri);
      
      // When:  a connection is made to the URI
      ResponseEntity<Map<String, Object>> response = 
          getRestTemplate().exchange(uri, HttpMethod.GET, null, 
              new ParameterizedTypeReference<>() {});
          
      //Then:  a not found status code of 404 is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      
      // And:  an error message is returned
      Map<String, Object> error = response.getBody();
      
      assertErrorMessageValid(error, HttpStatus.NOT_FOUND);
    } // testThatAnErrorMessageIsReturnedWhenAnUnknownExerciseNameIsSupplied method
    
    @ParameterizedTest
    @MethodSource("com.promineotech.workout.controller.FetchWorkoutTest#parametersForInvalidInput")
    void testThatAnErrorMessageIsReturnedWhenAnInvalidValueIsSupplied(
        String category, String reason) {
      // Given:  a valid category and URI
      String uri = String.format("%s?category=%s", getBaseUriForExercises(), category);
      
      //System.out.println(uri);
      
      // When:  a connection is made to the URI
      ResponseEntity<Map<String, Object>> response = 
          getRestTemplate().exchange(uri, HttpMethod.GET, null, 
              new ParameterizedTypeReference<>() {});
          
      //Then:  a not found status code of 400 is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
      
      // And:  an error message is returned
      Map<String, Object> error = response.getBody();
      
      assertErrorMessageValid(error, HttpStatus.BAD_REQUEST);
    } // testThatAnErrorMessageIsReturnedWhenAnInvalidValueIsSupplied method
    
    //@Test
  //void testThatExercisesAreReturnedWhenAValidTargetAreaIsSupplied() {
//    // Given:  a valid targetArea and URI
//    WorkoutTarget target = WorkoutTarget.ARMS;
//    String uri = String.format("%s?target=%s", getBaseUri(), target);
  //  
//    //System.out.println(uri);
  //  
//    // When:  a connection is made to the URI
//    ResponseEntity<Workout> response = getRestTemplate().getForEntity(uri, Workout.class);
  //  
//    //Then:  a status code of OK (200) is returned
//    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  //
  //} // testThatExercisesAreReturnedWhenAValidtargetAreaIsSupplied method
    
  } // TestsThatDoNotPolluteTheApplicationContext class
 
  static Stream<Arguments> parametersForInvalidInput() {
    // @formatter:off
    return Stream.of(
        //arguments("t8r*", "Category contains bad characters.")
        arguments("INVALID", "Category is not enum value.")
     // @formatter:on
        );        
  } // method parametersForInvalidInput
  
  @Nested
  @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
  @ActiveProfiles("test")
  @Sql(
      scripts = {"classpath:flyway/migrations/WorkoutLogsSchema.sql",
      "classpath:flyway/migrations/WorkoutLogData.sql"},
      config = @SqlConfig(encoding = "utf-8"))  
  class TestsThatPolluteTheApplicationContext extends FetchWorkoutTestSupport {
    @MockBean
    private ExercisesService workoutLogsService;
    
    /**
     * 
     */
//    @Test
//    void testThatAnAnUnplannedErrorResultsInA500Status() {
//      // Given:  a valid exercise name and URI
//      //String exerciseName = "Invalid";
//      //String uri = String.format("%s?exerciseName=%s", getBaseUri(), exerciseName);
//      ExerciseCategory category = ExerciseCategory.INVALID;
//      String uri = String.format("%s?category=%s", getBaseUri(), category);
//      
//      doThrow(new RuntimeException("Ouch!")).when(workoutLogsService).fetchExercises(category);
//      
//      // When:  a connection is made to the URI
//      ResponseEntity<Map<String, Object>> response = 
//          getRestTemplate().exchange(uri, HttpMethod.GET, null, 
//              new ParameterizedTypeReference<>() {});
//          
//      //Then:  a internal server error (500) status code is returned
//      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
//      
//      // And:  an error message is returned
//      Map<String, Object> error = response.getBody();
//      
//      assertErrorMessageValid(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    } // testThatAnAnUnplannedErrorResultsInA500Statu method
   
  }
  
 
    
} // FetchWorkoutTest class
