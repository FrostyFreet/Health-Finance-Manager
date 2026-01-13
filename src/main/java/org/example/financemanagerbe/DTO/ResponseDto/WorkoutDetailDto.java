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
    private Integer numberOfExercises;
    private Integer numberOfSets;

    public WorkoutDetailDto(Workout workout) {
        this.title = workout.getTitle();
        this.duration = workout.getDuration();
        this.createdAt = workout.getCreatedAt();
        this.exercises = workout.getWorkoutExercises() == null ? List.of() :
                workout.getWorkoutExercises().stream().map(WorkoutExerciseDto::new).toList();
        var exercises = workout.getWorkoutExercises();
        this.numberOfExercises = (exercises == null) ? 0 : exercises.size();

        int totalSets = (exercises == null) ? 0 :
                exercises.stream()
                        .flatMap(we -> we.getWorkoutSets() != null ? we.getWorkoutSets().stream() : java.util.stream.Stream.empty())
                        .mapToInt(ws -> ws.getNumberOfSets() != null ? ws.getNumberOfSets() : 0)
                        .sum();
        this.numberOfSets = totalSets;
    }
}
