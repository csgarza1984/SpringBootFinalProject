package com.promineotech.workout.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import com.promineotech.workout.entity.Exercise;
import com.promineotech.workout.entity.ExerciseCategory;
import com.promineotech.workout.entity.ExerciseTarget;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultExercisesDao implements ExcercisesDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
   
  @Override
  public List<Exercise> fetchExercises(ExerciseCategory category) {
    log.debug("DAO: category={}, exerciseName={}", category);
    
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM exercises "
        + "WHERE category = :category ";
//        + "LIMIT 2";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("category", category.toString());
    
    return jdbcTemplate.query(sql, params, new RowMapper<>() {
      @Override
      public Exercise mapRow(ResultSet rs, int rowNum) throws SQLException {
        // @formatter:off
        return Exercise.builder()
            .exerciseId(rs.getString("exercise_id"))
            .category(ExerciseCategory.valueOf(rs.getString("category")))
            .exerciseName(rs.getNString("exercise_name"))
            .targetArea(ExerciseTarget.valueOf(rs.getString("target_area")))
            .build();
        // @formatter:on
 
      }});   
  } //fetchExercises

//  @Override
//  public List<Exercise> fetchAllExercises() {
//    log.debug("DAO: no category");
//    
//    // @formatter:off
//    String sql = ""
//        + "SELECT * "
//        + "FROM exercises ";
////        + "WHERE category = :category ";
////        + "LIMIT 2";
//    // @formatter:on
//    
////    Map<String, Object> params = new HashMap<>();
////    params.put("category", category.toString());
//    
//    return jdbcTemplate.query(sql, new RowMapper<>() {
//      @Override
//      public Exercise mapRow(ResultSet rs, int rowNum) throws SQLException {
//        // @formatter:off
//        return Exercise.builder()
//            .exerciseId(rs.getString("exercise_id"))
//            .category(ExerciseCategory.valueOf(rs.getString("category")))
//            .exerciseName(rs.getNString("exercise_name"))
//            .targetArea(ExerciseTarget.valueOf(rs.getString("target_area")))
//            .build();
//        // @formatter:on
// 
//      }});   }

} // class
