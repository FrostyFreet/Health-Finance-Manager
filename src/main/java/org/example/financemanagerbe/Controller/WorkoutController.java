package org.example.financemanagerbe.Controller;

import org.example.financemanagerbe.DTO.RequestDto.WorkoutRequest;
import org.example.financemanagerbe.DTO.ResponseDto.WorkoutDetailDto;
import org.example.financemanagerbe.DTO.ResponseDto.WorkoutDto;
import org.example.financemanagerbe.Model.Workout;
import org.example.financemanagerbe.Service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController (WorkoutService workoutService){
        this.workoutService = workoutService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createWorkOut(@RequestBody WorkoutRequest workout) {
        return ResponseEntity.ok(workoutService.createWorkOut(workout));
    }
    @PostMapping("/{workoutId}/addExercise")
    public ResponseEntity<String> addExerciseToWorkout(@PathVariable Long workoutId, @RequestParam String exerciseName) {
        return ResponseEntity.ok(workoutService.addExerciseToWorkout(workoutId,exerciseName));
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<WorkoutDto>> getAllWorkouts() {
        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }
    @GetMapping("/getWorkoutsInOrder")
    public ResponseEntity<List<WorkoutDto>> getWorkoutsInOrder() {
        return ResponseEntity.ok(workoutService.getWorkoutsInOrder());
    }
    @GetMapping("/getLatestWorkout")
    public ResponseEntity<WorkoutDto> getLatestWorkout() {
        return ResponseEntity.ok(workoutService.getLatestWorkout());
    }
    @GetMapping("/updateWorkoutName")
    public ResponseEntity<WorkoutDetailDto> updateWorkoutName(@RequestParam Long id, @RequestParam String name) {
        return ResponseEntity.ok(workoutService.updateWorkoutName(id,name));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<WorkoutDetailDto> getWorkOutById(@PathVariable Long id) {
        return ResponseEntity.ok(workoutService.getWorkOutById(id));
    }


    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteWorkOutById(@PathVariable Long id) {
        return ResponseEntity.ok(workoutService.deleteWorkOutById(id));
    }

    @DeleteMapping("/deleteExerciseFromWorkoutById")
    public ResponseEntity<String> deleteExerciseFromWorkoutById(@RequestParam String workoutId, @RequestParam String exerciseId) {
        return ResponseEntity.ok(workoutService.deleteExerciseFromWorkoutById(workoutId,exerciseId));
    }

}
