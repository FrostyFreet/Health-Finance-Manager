package org.example.financemanagerbe.DTO.ResponseDto;

import lombok.Getter;
import lombok.Setter;
import org.example.financemanagerbe.Model.WorkoutExercise;

import java.util.List;

@Getter
@Setter
public class WorkoutExerciseDto {
    private Long id;
    private String name;
    private String muscleGroup;
    private List<WorkoutSetDto> sets;

    public WorkoutExerciseDto(WorkoutExercise we) {
        this.id = we.getId();
        if (we.getExercise() != null) {
            this.name = we.getExercise().getName();
            this.muscleGroup = we.getExercise().getMuscleGroup();
        }
        this.sets = we.getWorkoutSets() == null ? List.of() :
                we.getWorkoutSets().stream().map(WorkoutSetDto::new).toList();
    }
}
