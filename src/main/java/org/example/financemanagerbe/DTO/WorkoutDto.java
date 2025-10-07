package org.example.financemanagerbe.DTO;

import lombok.Getter;
import lombok.Setter;
import org.example.financemanagerbe.Model.Workout;

import java.time.LocalDateTime;

@Getter
@Setter
public class WorkoutDto {
    private String title;
    private Double duration;
    private LocalDateTime createdAt;

    public WorkoutDto (Workout workout) {
        this.title = workout.getTitle();
        this.duration = workout.getDuration();
        this.createdAt = workout.getCreatedAt();
    }

}
