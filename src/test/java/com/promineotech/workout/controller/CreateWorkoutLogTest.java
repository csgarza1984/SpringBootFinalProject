package com.promineotech.workout.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.workout.controller.support.CreateWorkoutLogTestSupport;
import com.promineotech.workout.entity.Workout;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(
    scripts = {"classpath:flyway/migrations/WorkoutLogsSchema.sql",
    "classpath:flyway/migrations/WorkoutLogData.sql"},
    config = @SqlConfig(encoding = "utf-8"))  
class CreateWorkoutLogTest extends CreateWorkoutLogTestSupport {

  /**
   * 
   */
  @Test
  void testCreateWorkoutLogReturnsSuccess201() {
    // Given a workout as JSON
    String body = createWorkoutBody();
    String uri = getBaseUriForWorkouts();
    
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    
    HttpEntity<String> bodyEntity = new HttpEntity<>(body);
    
    //When:  the workout log is sent
    ResponseEntity<Workout> response = getRestTemplate().exchange(uri, 
        HttpMethod.POST, bodyEntity, Workout.class);
    
    //Then:  a 201 status is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    
    // And:  the returned workout is correct
    assertThat(response.getBody()).isNotNull();
    
    Workout workout = response.getBody();
    assertThat(workout.getPerson().getPersonId()).isEqualTo("DENNIS_MURPHY");
    assertThat(workout.getWorkoutDate()).isEqualTo("2021-01-02");
  } // testCreateWorkoutLogReturnSuccess201 method

}  // class
