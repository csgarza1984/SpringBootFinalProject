package com.promineotech.workout.controller;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.workout.entity.Workout;
import com.promineotech.workout.entity.WorkoutRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/workouts")
@OpenAPIDefinition(info = @Info(title = "Workout Logs Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface WorkoutLogController {
  // @formatter:off
  @Operation(
      summary = "Create a workout",
      description = "Returns the created workout.",
      responses = {
          @ApiResponse(responseCode = "201", 
              description = "The created workout is returned.", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Workout.class))),
          @ApiResponse(responseCode = "400", 
              description = "The requested parameter is invalid.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404", 
              description = "No workout detail was found with the input criteria.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description = "An unplanned error occurred.", 
              content = @Content(mediaType = "application/json"))
      }, // responses
      parameters = {
          @Parameter(
              name = "workoutRequest", 
              required = true, 
              description = "The workout as JSON")         
      } //parameters list
  ) // Operation annotation
  
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Workout createWorkoutLog(@Valid @RequestBody WorkoutRequest workoutRequest);
  // @formatter:on
  
  @Operation(
      summary = "Delete a workout",
      description = "Deletes an existing workout.",
      responses = {
          @ApiResponse(responseCode = "200", 
              description = "The created workout was deleted.", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Workout.class))),
          @ApiResponse(responseCode = "400", 
              description = "The requested parameter is invalid.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404", 
              description = "No workout detail was found with the input criteria.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description = "An unplanned error occurred.", 
              content = @Content(mediaType = "application/json"))
      }, // responses
      parameters = {
          @Parameter(
              name = "deleteRequest", 
              required = true, 
              description = "The workout as JSON")         
      } //parameters list
  ) // Operation annotation
  
  @PostMapping("/delete")
  @ResponseStatus(code = HttpStatus.OK)
  Workout deleteWorkoutLog(@Valid @RequestBody WorkoutRequest workoutRequest); 
  
} // WorkoutLogsController interface
