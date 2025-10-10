package org.example.financemanagerbe.DTO.ResponseDto;

import lombok.Getter;
import lombok.Setter;
import org.example.financemanagerbe.Model.WorkoutSet;

@Getter
@Setter
public class WorkoutSetDto {
    private Long id;
    private Double weight;
    private Integer numberOfSets;
    private Double numberOfReps;

    public WorkoutSetDto(WorkoutSet set) {
        this.id = set.getId();
        this.weight = set.getWeight();
        this.numberOfSets = set.getNumberOfSets();
        this.numberOfReps = set.getNumberOfReps();
    }
}
