package org.example.financemanagerbe.DTO.ResponseDto;

import java.time.LocalDateTime;

public interface HighestWeightLastWorkoutProjection {
    Long getWorkoutExerciseId();
    Long getExerciseId();
    String getExerciseName();
    Double getMaxWeightLastWorkout();
    Integer getMaxNumberOfReps();
    LocalDateTime getWorkoutExerciseCreatedAt();
    LocalDateTime getWorkoutCreatedAt();
}
