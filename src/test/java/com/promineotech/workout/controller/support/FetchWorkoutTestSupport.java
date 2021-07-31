package com.promineotech.workout.controller.support;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import com.promineotech.workout.entity.Exercise;
import com.promineotech.workout.entity.ExerciseCategory;
import com.promineotech.workout.entity.ExerciseTarget;

public class FetchWorkoutTestSupport extends BaseTest {
  protected List<Exercise> buildExpected() {
    List<Exercise> list = new LinkedList<Exercise>();
    
    // @formatter:off    
    list.add(Exercise.builder()
        .exerciseId("CHEST_PRESS")
        .category(ExerciseCategory.STRENGTH)
        .exerciseName("Chest Press")
        .targetArea(ExerciseTarget.CHEST)
        .build());   
    
    list.add(Exercise.builder()
        .exerciseId("CHEST_FLYE")
        .category(ExerciseCategory.STRENGTH)
        .exerciseName("Chest Flye")
        .targetArea(ExerciseTarget.CHEST)
        .build());
    //@formatter:on
    
    Collections.sort(list);
    return list;
  }
  
  /**
   * 
   * @param error
   * @param status
   */
  protected void assertErrorMessageValid(Map<String, Object> error, 
      HttpStatus status) {
    // @formatter:off
    assertThat(error)
      .containsKey("message")
      .containsEntry("status code", status.value())
      // .containsEntry("uri", "/workouts")
      .containsKey("timestamp")
      .containsEntry("reason", status.getReasonPhrase());
    // @formatter:on
  }
 

} // FetchWorkoutSupport class
