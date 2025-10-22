package org.example.financemanagerbe.Service;

import org.example.financemanagerbe.DTO.ResponseDto.ExerciseDto;
import org.example.financemanagerbe.DTO.ResponseDto.WeightProgressDto;
import org.example.financemanagerbe.Model.Exercise;
import org.example.financemanagerbe.Model.User;
import org.example.financemanagerbe.Model.WorkoutSet;
import org.example.financemanagerbe.Repository.ExerciseRepistory;
import org.example.financemanagerbe.Repository.WorkoutSetRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepistory exerciseRepistory;
    private final WorkoutSetRepository workoutSetRepository;
    private final Utility utility;

    public ExerciseService (ExerciseRepistory exerciseRepistory, WorkoutSetRepository workoutSetRepository, Utility utility){
        this.utility = utility;
        this.exerciseRepistory = exerciseRepistory;
        this.workoutSetRepository = workoutSetRepository;
    }

    public Exercise createExercise(Exercise exercise) {
        try {
            return exerciseRepistory.save(exercise);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Invalid exercise data", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create exercise", e);
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
    public List<WeightProgressDto> getWeightProgress(
            Long exerciseId,
            LocalDateTime startDate,
            LocalDateTime endDate
    ) {
        User currentUser = utility.getCurrentUser();
        List<WorkoutSet> sets = workoutSetRepository
                .findByExerciseAndDateRange(
                        currentUser.getId(),
                        exerciseId,
                        startDate,
                        endDate
                );

        return sets.stream()
                .map(WeightProgressDto::new)
                .toList();
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
