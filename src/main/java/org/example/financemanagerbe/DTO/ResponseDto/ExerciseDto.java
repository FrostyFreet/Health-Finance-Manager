package org.example.financemanagerbe.DTO.ResponseDto;

import lombok.Getter;
import lombok.Setter;
import org.example.financemanagerbe.Model.Exercise;

@Getter
@Setter
public class ExerciseDto {
    private Long id;
    private String name;
    private String muscleGroup;

    public ExerciseDto (Exercise exercise){
        this.id = exercise.getId();
        this.name = exercise.getName();
        this.muscleGroup = exercise.getMuscleGroup();
    }

}
