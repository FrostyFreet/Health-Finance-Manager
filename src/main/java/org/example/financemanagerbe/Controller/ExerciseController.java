package org.example.financemanagerbe.Controller;

import org.example.financemanagerbe.DTO.ResponseDto.ExerciseDto;
import org.example.financemanagerbe.DTO.ResponseDto.WeightProgressDto;
import org.example.financemanagerbe.Model.Exercise;
import org.example.financemanagerbe.Service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController (ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createExercise(@RequestBody Exercise exercise) {
        try {
            Exercise saved = exerciseService.createExercise(exercise);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of(
                            "message", "Exercise created successfully",
                            "id", saved.getId(),
                            "name", saved.getName()
                    ));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Invalid exercise data"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected server error"));
        }
    }

    @GetMapping("/{id}/weight-progress")
    public ResponseEntity<List<WeightProgressDto>> getWeightProgress(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
            ) {
        return ResponseEntity.ok(
                exerciseService.getWeightProgress(id, startDate, endDate)
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ExerciseDto>> getAllExercises(){
        return ResponseEntity.ok(exerciseService.getAllExercises());
    }
    @GetMapping("/getExerciseByName")
    public ResponseEntity<List<ExerciseDto>> getExerciseByName(@RequestParam String name){
        return ResponseEntity.ok(exerciseService.getExerciseByName(name));
    }

    @GetMapping("/getAllByMuscleGroup")
    public ResponseEntity<List<ExerciseDto>> getAllExercisesByMuscleGroup(@RequestParam String name){
        return ResponseEntity.ok(exerciseService.getAllExercisesByMuscleGroup(name));
    }


    @GetMapping("/getHighestWeightLastWorkoutByExerciseId/{exerciseId}")
    public ResponseEntity<?> getHighestWeightLastWorkoutByExerciseId(@PathVariable Long exerciseId) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(exerciseService.getHighestWeightLastWorkoutByExerciseId(exerciseId));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Invalid exercise data"));
        }

    }

    @GetMapping("/getHighestWeightEverUsedByExerciseId/{exerciseId}")
    public ResponseEntity<?> getHighestWeightEverUsedByExerciseId(@PathVariable Long exerciseId) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(exerciseService.getHighestWeightEverUsedByExerciseId(exerciseId));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Invalid exercise data"));
        }

    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteExerciseById(@PathVariable Long id){
        return ResponseEntity.ok(exerciseService.deleteExerciseById(id));
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<String> updateExerciseById(@PathVariable Long id, Exercise exercise){
        return ResponseEntity.ok(exerciseService.updateExerciseById(id,exercise));
    }
}
