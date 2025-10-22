package org.example.financemanagerbe.DTO.ResponseDto;

import lombok.Getter;
import lombok.Setter;
import org.example.financemanagerbe.Model.WorkoutSet;
import java.time.LocalDateTime;
@Getter
@Setter
public class WeightProgressDto {
    private Long workoutId;
    private String workoutTitle;
    private LocalDateTime workoutDate;
    private Long exerciseId;
    private String exerciseName;
    private Double weight;
    private Double numberOfReps;
    private Integer numberOfSets;

    public WeightProgressDto(WorkoutSet workoutSet) {
        this.workoutId = workoutSet.getWorkoutExercise().getWorkout().getId();
        this.workoutTitle = workoutSet.getWorkoutExercise().getWorkout().getTitle();
        this.workoutDate = workoutSet.getWorkoutExercise().getWorkout().getCreatedAt();
        this.exerciseId = workoutSet.getWorkoutExercise().getExercise().getId();
        this.exerciseName = workoutSet.getWorkoutExercise().getExercise().getName();
        this.weight = workoutSet.getWeight();
        this.numberOfReps = workoutSet.getNumberOfReps();
        this.numberOfSets = workoutSet.getNumberOfSets();
    }


}