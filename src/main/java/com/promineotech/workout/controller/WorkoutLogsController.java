package com.promineotech.workout.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.workout.entity.Exercise;
import com.promineotech.workout.entity.ExerciseCategory;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@RequestMapping("/workouts")
@OpenAPIDefinition(info = @Info(title = "Workouts Log Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface WorkoutLogsController {
  // @formatter:off
  @Operation(
      summary = "Returns a list of Exercises",
      description = "Returns a list of Exercises given a category",
      responses = {
          @ApiResponse(responseCode = "200", 
              description = "A list of Exercises is returned.", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Exercise.class))),
          @ApiResponse(responseCode = "400", 
              description = "The request parameter is invalid.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404", 
              description = "No Exercises were found with the input criteria.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description = "An unplanned error occurred.", 
              content = @Content(mediaType = "application/json"))
      }, // responses
      parameters = {
          @Parameter(
              name = "category", 
              allowEmptyValue = false, 
              required = false, 
              description = "The exercise category (i.e., 'STRENGTH')"),         
      } //parameters list
  ) // Operation annotation
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Exercise> fetchWorkouts(
      @RequestParam(required = false) 
        ExerciseCategory category);
  // @formatter:on
  
} // WorkoutLogsController interface
