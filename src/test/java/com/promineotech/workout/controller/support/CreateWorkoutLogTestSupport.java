package com.promineotech.workout.controller.support;

public class CreateWorkoutLogTestSupport extends BaseTest {
  /**
   * 
   * @return
   */
  protected String createWorkoutBody() {
    // @formatter:off
    return "{\n"
        + "   \"person\":\"DENNIS_MURPHY\",\n"
        + "   \"workoutDate\":\"2021-01-02\",\n"
        + "}";
    // @formatter:on
  } // createWorkoutBody method

} // class
