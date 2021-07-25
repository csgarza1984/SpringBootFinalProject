package com.promineotech.workout.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.promineotech.workout.controller.support.FetchWorkoutTestSupport;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FetchWorkoutTest extends FetchWorkoutTestSupport {

  @Test
  void test() {
    System.out.println(getBaseUri());
  } // test method

} // FetchWorkoutTest class
