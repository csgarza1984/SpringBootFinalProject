DROP TABLE IF EXISTS exercise_details;
DROP TABLE IF EXISTS workout_exercises;
DROP TABLE IF EXISTS workouts;
DROP TABLE IF EXISTS exercises;
DROP TABLE IF EXISTS people;

CREATE TABLE people (
  person_pk int unsigned NOT NULL AUTO_INCREMENT,
  person_id varchar(40) NOT NULL,
  first_name varchar(45) NOT NULL, 
  last_name varchar(45) NOT NULL,
  email varchar(250),
  PRIMARY KEY (person_pk)
);

CREATE TABLE exercises (
  exercise_pk int unsigned NOT NULL AUTO_INCREMENT,
  exercise_id varchar(30) NOT NULL,
  category enum('CARDIO', 'STRENGTH'),
  exercise_name varchar(60) NOT NULL,
  target_area enum('CHEST', 'SHOULDER', 'BACK', 'ARMS', 'ABS', 'LEGS', 'HEART') NOT NULL,
  PRIMARY KEY (exercise_pk),
  UNIQUE KEY (exercise_id)
);

CREATE TABLE workouts (
  workout_pk int unsigned NOT NULL AUTO_INCREMENT,
  person_fk int unsigned NOT NULL,
  workout_date varchar(10) NOT NULL,
  PRIMARY KEY (workout_pk),
  FOREIGN KEY (person_fk) REFERENCES people (person_pk) ON DELETE CASCADE
);

CREATE TABLE workout_exercises (
  exercise_fk int unsigned NOT NULL,
  workout_fk int unsigned NOT NULL,
  FOREIGN KEY (exercise_fk) REFERENCES exercises (exercise_pk) ON DELETE CASCADE,
  FOREIGN KEY (workout_fk) REFERENCES workouts (workout_pk) ON DELETE CASCADE
);

CREATE TABLE exercise_details (
  set_pk int unsigned NOT NULL AUTO_INCREMENT,
  exercise_fk int unsigned NOT NULL,
  num_reps int unsigned,
  weight int unsigned,
  duration_in_minutes int unsigned,
  distance decimal(5, 2),
  PRIMARY KEY (set_pk),
  FOREIGN KEY (exercise_fk) REFERENCES exercises (exercise_pk) ON DELETE CASCADE
);