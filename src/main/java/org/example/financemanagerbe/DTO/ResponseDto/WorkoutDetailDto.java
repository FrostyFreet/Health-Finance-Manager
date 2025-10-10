package org.example.financemanagerbe.DTO.ResponseDto;

import lombok.Getter;
import lombok.Setter;
import org.example.financemanagerbe.Model.Workout;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class WorkoutDetailDto {
    private String title;
    private Double duration;
    private LocalDateTime createdAt;
    private List<WorkoutExerciseDto> exercises;

    public WorkoutDetailDto(Workout workout) {
        this.title = workout.getTitle();
        this.duration = workout.getDuration();
        this.createdAt = workout.getCreatedAt();
        this.exercises = workout.getWorkoutExercises() == null ? List.of() :
                workout.getWorkoutExercises().stream().map(WorkoutExerciseDto::new).toList();
    }
}
