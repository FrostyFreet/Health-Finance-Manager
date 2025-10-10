package org.example.financemanagerbe.DTO.ResponseDto;

import lombok.Getter;
import lombok.Setter;
import org.example.financemanagerbe.Model.Workout;

import java.time.LocalDateTime;

@Getter
@Setter
public class WorkoutDto {
    private Long id;
    private String title;
    private Double duration;
    private Integer numberOfExercises;
    private LocalDateTime createdAt;

    public WorkoutDto (Workout workout) {
        this.title = workout.getTitle();
        this.id = workout.getId();
        this.duration = workout.getDuration();
        this.createdAt = workout.getCreatedAt();
        this.numberOfExercises = workout.getWorkoutExercises().size();
    }

}
