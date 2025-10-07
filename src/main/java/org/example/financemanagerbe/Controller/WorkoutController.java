package org.example.financemanagerbe.Controller;

import org.example.financemanagerbe.DTO.WorkoutDto;
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
    public ResponseEntity<String> createWorkOut(@RequestBody Workout workout) {
        return ResponseEntity.ok(workoutService.createWorkOut(workout));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<WorkoutDto>> getAllWorkouts() {
        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<WorkoutDto> getWorkOutById(@PathVariable Long id) {
        return ResponseEntity.ok(workoutService.getWorkOutById(id));
    }


    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteWorkOutById(@PathVariable Long id) {
        return ResponseEntity.ok(workoutService.deleteWorkOutById(id));
    }

    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity<String> deleteWorkOutByName(@PathVariable String name) {
        return ResponseEntity.ok(workoutService.deleteWorkOutByName(name));
    }
}
