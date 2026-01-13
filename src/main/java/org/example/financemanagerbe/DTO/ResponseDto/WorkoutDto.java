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
    private Double totalVolume;
    private Integer numberOfSets;
    private LocalDateTime createdAt;

    public WorkoutDto (Workout workout) {
        this.title = workout.getTitle();
        this.id = workout.getId();
        this.duration = workout.getDuration();
        this.createdAt = workout.getCreatedAt();

        var exercises = workout.getWorkoutExercises();
        this.numberOfExercises = (exercises == null) ? 0 : exercises.size();
        int totalSets = (exercises == null) ? 0 :
                exercises.stream()
                        .flatMap(we -> we.getWorkoutSets() != null ? we.getWorkoutSets().stream() : java.util.stream.Stream.empty())
                        .mapToInt(ws -> ws.getNumberOfSets() != null ? ws.getNumberOfSets() : 0)
                        .sum();
        this.numberOfSets = totalSets;

        this.totalVolume = workout.getWorkoutExercises() == null ? 0.0 :
                workout.getWorkoutExercises().stream()
                        .flatMap(we -> we.getWorkoutSets() != null ? we.getWorkoutSets().stream() : java.util.stream.Stream.empty())
                        .mapToDouble(ws -> {
                            double w = ws.getWeight() != null ? ws.getWeight() : 0.0;
                            double r = ws.getNumberOfReps() != null ? ws.getNumberOfReps() : 0.0;
                            int s = ws.getNumberOfSets() != null ? ws.getNumberOfSets() : 0;
                            return w * r * s;
                        })
                        .sum();
    }

}
