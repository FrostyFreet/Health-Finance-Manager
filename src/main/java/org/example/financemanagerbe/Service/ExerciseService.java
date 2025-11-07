package org.example.financemanagerbe.Service;

import org.example.financemanagerbe.DTO.ResponseDto.*;
import org.example.financemanagerbe.Model.Exercise;
import org.example.financemanagerbe.Model.User;
import org.example.financemanagerbe.Model.WorkoutExercise;
import org.example.financemanagerbe.Model.WorkoutSet;
import org.example.financemanagerbe.Repository.ExerciseRepository;
import org.example.financemanagerbe.Repository.WorkoutExerciseRepository;
import org.example.financemanagerbe.Repository.WorkoutSetRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final WorkoutSetRepository workoutSetRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final Utility utility;

    public ExerciseService (ExerciseRepository exerciseRepository, WorkoutSetRepository workoutSetRepository, Utility utility, WorkoutExerciseRepository workoutExerciseRepository){
        this.utility = utility;
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutSetRepository = workoutSetRepository;
    }

    public Exercise createExercise(Exercise exercise) {
        try {
            return exerciseRepository.save(exercise);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Invalid exercise data", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create exercise", e);
        }
    }

    public List<ExerciseDto> getAllExercises(){
        return exerciseRepository.findAll().stream().map(ExerciseDto::new).toList();
    }

    public List<ExerciseDto> getExerciseByName(String name){

        return exerciseRepository.findByNameContainingIgnoreCase((name)).stream().map(ExerciseDto::new).toList();
    }

    public List<ExerciseDto> getAllExercisesByMuscleGroup(String name){
        return exerciseRepository.findAllByMuscleGroup(name).stream().map(ExerciseDto::new).toList();
    }

    public WorkoutSetDto getHighestWeightEverUsedByExerciseId(Long exerciseId){
        WorkoutSet topSet = workoutExerciseRepository
                .findHighestWeightEverByExerciseIdAndUserId(exerciseId, utility.getCurrentUser().getId())
                .orElseThrow(() -> new RuntimeException("No sets found for exercise id: " + exerciseId));
        return new WorkoutSetDto(topSet);
    }

    public HighestWeightLastWorkoutProjection getHighestWeightLastWorkoutByExerciseId(Long exerciseId){
        return  workoutExerciseRepository
                .findHighestWeightLastWorkoutByExerciseIdAndUserId(exerciseId, utility.getCurrentUser().getId())
                .orElseThrow(() -> new RuntimeException("No sets found for latest workout_exercise id: " + exerciseId));
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
            Exercise foundExercise = exerciseRepository.findById(id).orElse(null);
            if (foundExercise == null) return "No exercise found!";

            if (exercise.getName() != null) foundExercise.setName(exercise.getName());
            if (exercise.getMuscleGroup() != null) foundExercise.setMuscleGroup(exercise.getMuscleGroup());

            exerciseRepository.save(foundExercise);

            return "Updated!";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteExerciseById(Long id){
        try {
            Exercise foundExercise = exerciseRepository.findById(id).orElse(null);
            if (foundExercise == null) return "No exercise found!";
            exerciseRepository.delete(foundExercise);

            return "Deleted!";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
