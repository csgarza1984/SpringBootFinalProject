package com.promineotech.workout.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.promineotech.workout.entity.Exercise;
import com.promineotech.workout.entity.ExerciseCategory;
import com.promineotech.workout.entity.ExerciseDetails;
import com.promineotech.workout.entity.ExerciseTarget;
import com.promineotech.workout.entity.Person;
import com.promineotech.workout.entity.Workout;

@Component
public class DefaultWorkoutLogDao implements WorkoutLogDao {
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  /**
   * 
   */
  @Override
  public Optional<Person> fetchPerson(String personId) {
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM people "
        + "WHERE person_id = :person_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("person_id", personId);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new PersonResultSetExtractor()));
  } // fetchPerson method
  
  class PersonResultSetExtractor implements ResultSetExtractor<Person> {
    @Override
    public Person extractData(ResultSet rs) throws SQLException {
      rs.next();
      
      // @formatter:off
      return Person.builder()
          .personId(rs.getString("person_id"))
          .personPK(rs.getLong("person_pk"))          
          .firstName(rs.getString("first_name"))
          .lastName(rs.getString("last_name"))
          .email(rs.getString("email"))
          .build();
      // @formatter:on
    }
  } // PersonResultSetExtractor inner class

//  @Override
//  public Optional<String> fetchWorkoutDate(Date workoutDate) {
//    return Optional.empty();    
//  }
  
  @Override
  public Optional<String> fetchWorkoutDate(String workoutDate) {
    return Optional.empty();    
  }

  public Workout saveWorkout(Person person, String workoutDate, 
      List<Exercise> exercises, List<ExerciseDetails> exerciseDetails) {
    SqlParams params =
        generateInsertSql(person, workoutDate);
    
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);
    
    Long workoutPK = keyHolder.getKey().longValue();
    saveExercises(exercises, workoutPK);
    
    // @formatter:off
    return Workout.builder()
        .workoutPK(workoutPK)
        .person(person)
        .workoutDate(workoutDate)
        .exercises(exercises)
        .build();        
    // @formatter:on
  } // saveWorkout method
  
  /**
   * 
   * @param exercises
   * @param workoutPK
   */
  private void saveExercises(List<Exercise> exercises, Long workoutPK) {
    for(Exercise exercise : exercises) {
      SqlParams params = generateInsertSql(exercise, workoutPK);
      jdbcTemplate.update(params.sql, params.source);
    }
  } // saveExercises method
  
  /**
   * 
   * @param exercise
   * @return
   */
  private SqlParams generateInsertSql(Exercise exercise, Long workoutPK) {
    SqlParams params = new SqlParams();
    
    // @formatter:off
    params.sql = ""
        + "INSERT INTO workout_exercises ("
        + "exercise_fk, workout_fk"
        + ") VALUES ("
        + ":exercise_fk, :workout_fk"
        + ")";
    // @formatter:on
    
    params.source.addValue("exercise_fk", exercise.getExercisePK());
    params.source.addValue("workout_fk", workoutPK);
    
    return params;
    
  } // generateInsertSql - Exercise info
  
  private SqlParams generateInsertSql(Person person, String workoutDate) {
    // @formatter:off
    String sql = ""
        + "INSERT INTO workouts ("
        + "person_fk, workout_date"
        + ") VALUES ("
        + ":person_fk, :workout_date"
        + ")";
 // @formatter:on
    
    SqlParams params = new SqlParams();
    
    params.sql = sql;
    params.source.addValue("person_fk", person.getPersonPK());
    params.source.addValue("workout_date", workoutDate);
    
    return params;
  } // generateInsertSql - workouts

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  } //  SqlParams class

  @Override
  public List<Exercise> fetchExercises(List<String> exerciseIds) {
    if(exerciseIds.isEmpty()) {
      return new LinkedList<>();
    }
    
    Map<String, Object> params = new HashMap<>();
    
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM exercises "
        + "WHERE exercise_id IN(";
    // @formatter:on    

    for (int index = 0; index < exerciseIds.size(); index++) {
      String key = "exercise_" + index;
      sql += ":" + key + ",";
      params.put(key,  exerciseIds.get(index));
    }
    
    sql = sql.substring(0, sql.length()-2);
    sql += ")";
    
    return jdbcTemplate.query(sql, params, new RowMapper<Exercise>() {
      @Override
      public Exercise mapRow(ResultSet rs, int rowNum) throws SQLException {
        // @formatter:off
        return Exercise.builder()
            .category(ExerciseCategory.valueOf(rs.getString("category")))
            .targetArea(ExerciseTarget.valueOf(rs.getString("target_area")))
            .exerciseName(rs.getString("exercise_name"))
            .exerciseId(rs.getString("exercise_id"))
            .exercisePK(rs.getLong("exercise_pk"))
            .build();
        // @formatter:on
      }
    });
  } // fetchExercises method
  
  @Override
  public List<ExerciseDetails> fetchExerciseDetails(List<String> exerciseDetails) {
    if(exerciseDetails.isEmpty()) {
      return new LinkedList<>();
    }
    
    Map<String, Object> params = new HashMap<>();
    
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM exercises_details "
        + "WHERE exercise_fk IN(";
    // @formatter:on    

    for (int index = 0; index < exerciseDetails.size(); index++) {
      String key = "exercise_details_" + index;
      sql += ":" + key + ",";
      params.put(key,  exerciseDetails.get(index));
    }
    
    sql = sql.substring(0, sql.length()-2);
    sql += ")";
    
    return jdbcTemplate.query(sql, params, new RowMapper<ExerciseDetails>() {
      @Override
      public ExerciseDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        // @formatter:off
        return ExerciseDetails.builder()
            .setPK(rs.getLong("set_pk"))
            .exerciseFK(rs.getLong("exercise_fk"))
            .numReps(rs.getInt("num_reps"))
            .weight(rs.getInt("weight"))
            .durationInMinutes(rs.getInt("duration_in_minutes"))
            .distance(rs.getInt("distance"))
            .build();
        // @formatter:on
      }
    });
  } // fetchExerciseDetails method

  @Override
  public void deleteWorkout(Long person_fk, String workoutDate) {
    // @formatter:off
    String sql = ""
        + "DELETE * "
        + "FROM workouts "
        + "WHERE person_fk = :person_fk "
        + "AND workout_id = :workoutDate";
    // @formatter:on    
    
    Map<String, Object> params = new HashMap<>();
    params.put("person_fk", personPK);
    params.put("workout_date", workoutDate);

  }

} // class
