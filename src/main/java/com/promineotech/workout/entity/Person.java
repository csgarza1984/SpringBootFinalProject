package com.promineotech.workout.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
  private Long personPK;
  private String personId;
  private String firstName;
  private String lastName;
  private String email;
  
  @JsonIgnore
  public Long getPersonPK() {
    return personPK;
  }
}
