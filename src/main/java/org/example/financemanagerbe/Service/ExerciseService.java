package org.example.financemanagerbe.Service;

import org.example.financemanagerbe.DTO.ResponseDto.ExerciseDto;
import org.example.financemanagerbe.Model.Exercise;
import org.example.financemanagerbe.Repository.ExerciseRepistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepistory exerciseRepistory;

    public ExerciseService (ExerciseRepistory exerciseRepistory){
        this.exerciseRepistory = exerciseRepistory;
    }

    public String createExercise(Exercise exercise){
        try{
            Exercise savedExercise = new Exercise();
            savedExercise.setName(exercise.getName());
            savedExercise.setMuscleGroup(exercise.getMuscleGroup());

            exerciseRepistory.save(savedExercise);

            return "Created";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ExerciseDto> getAllExercises(){
        return exerciseRepistory.findAll().stream().map(ExerciseDto::new).toList();
    }

    public List<ExerciseDto> getExerciseByName(String name){

        return exerciseRepistory.findByNameContainingIgnoreCase((name)).stream().map(ExerciseDto::new).toList();
    }

    public List<ExerciseDto> getAllExercisesByMuscleGroup(String name){
        return exerciseRepistory.findAllByMuscleGroup(name).stream().map(ExerciseDto::new).toList();
    }

    public String updateExerciseById(Long id, Exercise exercise){
        try {
            Exercise foundExercise = exerciseRepistory.findById(id).orElse(null);
            if (foundExercise == null) return "No exercise found!";

            if (exercise.getName() != null) foundExercise.setName(exercise.getName());
            if (exercise.getMuscleGroup() != null) foundExercise.setMuscleGroup(exercise.getMuscleGroup());

            exerciseRepistory.save(foundExercise);

            return "Updated!";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteExerciseById(Long id){
        try {
            Exercise foundExercise = exerciseRepistory.findById(id).orElse(null);
            if (foundExercise == null) return "No exercise found!";
            exerciseRepistory.delete(foundExercise);

            return "Deleted!";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
