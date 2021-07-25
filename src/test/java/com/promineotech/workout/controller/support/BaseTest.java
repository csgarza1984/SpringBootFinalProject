package com.promineotech.workout.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import lombok.Getter;

public class BaseTest {
  @LocalServerPort
  private int serverPort;
  
  @Autowired
  @Getter
  private TestRestTemplate restTemplate;
  
  //retrieve the uri of the request being sent to the application running under STS
  protected String getBaseUri() {
    return String.format("http://localhost:%d/workouts", serverPort);
  } // getBaseUri method
  
} // BaseTest Class
