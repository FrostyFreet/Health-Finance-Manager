package org.example.financemanagerbe.Service;

import org.example.financemanagerbe.DTO.RequestDto.SetRequest;
import org.example.financemanagerbe.DTO.ResponseDto.WorkoutSetDto;
import org.example.financemanagerbe.Model.WorkoutExercise;
import org.example.financemanagerbe.Model.WorkoutSet;
import org.example.financemanagerbe.Repository.WorkoutExerciseRepository;
import org.example.financemanagerbe.Repository.WorkoutSetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutSetService {

    private final WorkoutSetRepository workoutSetRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;

    public WorkoutSetService(WorkoutSetRepository workoutSetRepository, WorkoutExerciseRepository workoutExerciseRepository){
        this.workoutSetRepository = workoutSetRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    public String createSet(WorkoutSet workoutSet) {
        try {
            if (workoutSet.getWorkoutExercise() == null ||
                    workoutSet.getWorkoutExercise().getId() == null) {
                return "WorkoutExercise ID is required!";
            }
            WorkoutExercise workoutExercise = workoutExerciseRepository
                    .findById(workoutSet.getWorkoutExercise().getId())
                    .orElse(null);

            if (workoutExercise == null) {
                return "WorkoutExercise not found!";
            }

            WorkoutSet newSet = new WorkoutSet();
            newSet.setWorkoutExercise(workoutExercise);
            newSet.setNumberOfReps(workoutSet.getNumberOfReps());
            newSet.setNumberOfSets(workoutSet.getNumberOfSets());
            newSet.setWeight(workoutSet.getWeight());

            workoutSetRepository.save(newSet);
            return "Set created!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while creating set: " + e.getMessage();
        }
    }

    public List<WorkoutSetDto> getAllSetsByExerciseId(Long id){
        return workoutSetRepository.findAllByWorkoutExercise_Id(id).stream().map(WorkoutSetDto::new).toList();
    }

    public WorkoutSetDto deleteSetByExerciseId(Long id){
        WorkoutSet foundExercise = workoutSetRepository.findById(id).orElseThrow(()-> new RuntimeException("No exercise found!"));
        workoutSetRepository.delete(foundExercise);
        return new WorkoutSetDto(foundExercise);
    }

    public WorkoutSetDto updateSetById(Long id, SetRequest request){
        WorkoutSet foundExercise = workoutSetRepository.findById(id).orElseThrow(()-> new RuntimeException("No set found!"));
        if (request.getReps() != null &&request.getWeight() != null){
            foundExercise.setWeight(request.getWeight());
            foundExercise.setNumberOfReps(request.getReps());
            workoutSetRepository.save(foundExercise);

            return new WorkoutSetDto(foundExercise);
        }
        return null;

    }
}
