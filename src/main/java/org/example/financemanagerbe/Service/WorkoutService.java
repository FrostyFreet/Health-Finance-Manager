package org.example.financemanagerbe.Service;

import org.example.financemanagerbe.DTO.RequestDto.WorkoutRequest;
import org.example.financemanagerbe.DTO.ResponseDto.WorkoutDetailDto;
import org.example.financemanagerbe.DTO.ResponseDto.WorkoutDto;
import org.example.financemanagerbe.Model.*;
import org.example.financemanagerbe.Repository.ExerciseRepistory;
import org.example.financemanagerbe.Repository.WorkoutExerciseRepository;
import org.example.financemanagerbe.Repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {


    private final WorkoutRepository workoutRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final ExerciseRepistory exerciseRepistory;
    private final Utility utility;

    @Autowired
    public WorkoutService (WorkoutRepository workoutRepository, Utility utility, ExerciseRepistory exerciseRepistory,WorkoutExerciseRepository workoutExerciseRepository){
        this.exerciseRepistory = exerciseRepistory;
        this.workoutRepository = workoutRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.utility = utility;
    }
    public String createWorkOut(WorkoutRequest workout){
        try{
            User currentUser = utility.getCurrentUser();

            Workout savedWorkout = new Workout();
            savedWorkout.setTitle(workout.getTitle());
            savedWorkout.setDuration(workout.getDuration());
            savedWorkout.setUser(currentUser);

            workoutRepository.save(savedWorkout);
            return "Created";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String addExerciseToWorkout(Long id, String name){
        Exercise foundExercise = exerciseRepistory.findByName(name);
        Workout foundWorkout = workoutRepository.findById(id).orElse(null);
        if (foundExercise == null || foundWorkout == null){
            return "No exercise or workout found!";
        }

        boolean alreadyExists = foundWorkout.getWorkoutExercises().stream()
                .anyMatch(we -> we.getExercise().getId().equals(foundExercise.getId()));

        if (alreadyExists) return "Exercise already added to this workout.";


        WorkoutExercise workoutExercise = new WorkoutExercise();
        workoutExercise.setExercise(foundExercise);
        workoutExercise.setWorkout(foundWorkout);

        foundExercise.getWorkoutExercises().add(workoutExercise);

        workoutRepository.save(foundWorkout);

        return "Exercise added to workout successfully.";
    }



    public List<WorkoutDto> getAllWorkouts(){
        User currentUser = utility.getCurrentUser();
        List<Workout> workoutList = workoutRepository.findAllByUser(currentUser);
        return workoutList.stream().map(WorkoutDto::new).toList();
    }
    public List<WorkoutDto> getWorkoutsInOrder(){
        User currentUser = utility.getCurrentUser();
        List<Workout> workoutList = workoutRepository.findAllByUserOrderByCreatedAtDesc(currentUser);
        return workoutList.stream().map(WorkoutDto::new).toList();
    }
    public WorkoutDto getLatestWorkout(){
        User currentUser = utility.getCurrentUser();
        List<Workout> workoutList = workoutRepository.findAllByUserOrderByCreatedAtDesc(currentUser);

        return workoutList.isEmpty() ? null : new WorkoutDto(workoutList.getFirst());
    }

    public WorkoutDetailDto getWorkOutById(Long id){
        Workout foundWorkout = workoutRepository.findById(id).orElse(null);
        if (foundWorkout == null) return null;

        return new WorkoutDetailDto(foundWorkout);

    }

    public WorkoutDetailDto updateWorkoutName(Long id, String name){
        Workout foundWorkout = workoutRepository.findById(id).orElse(null);
        if (foundWorkout == null) return null;

        if(name != null){
            foundWorkout.setTitle(name);
            workoutRepository.save(foundWorkout);
        }

        return new WorkoutDetailDto(foundWorkout);

    }

    public String deleteWorkOutById(Long id){
        try{
            Workout foundWorkout = workoutRepository.findById(id).orElse(null);
            if (foundWorkout == null) return "No workout found!";

            workoutRepository.delete(foundWorkout);
            return "Deleted";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String deleteExerciseFromWorkoutById(String workoutId, String exerciseId){
        try{
            Workout foundWorkout = workoutRepository.findById(Long.valueOf(workoutId)).orElse(null);
            if (foundWorkout == null) return "No workout found!";
            WorkoutExercise exercise = workoutExerciseRepository.findById(Long.valueOf(exerciseId)).orElse(null);
            if (exercise == null) return "No exercise found!";
            foundWorkout.getWorkoutExercises().remove(exercise);

            workoutExerciseRepository.delete(exercise);

            workoutRepository.save(foundWorkout);
            return "Deleted";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
