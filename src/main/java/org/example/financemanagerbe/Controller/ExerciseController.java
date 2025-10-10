package org.example.financemanagerbe.Controller;

import org.example.financemanagerbe.DTO.ResponseDto.ExerciseDto;
import org.example.financemanagerbe.Model.Exercise;
import org.example.financemanagerbe.Service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController (ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createExercise(@RequestBody Exercise exercise){
        return ResponseEntity.ok(exerciseService.createExercise(exercise));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ExerciseDto>> getAllExercises(){
        return ResponseEntity.ok(exerciseService.getAllExercises());
    }
    @GetMapping("/getExerciseByName")
    public ResponseEntity<ExerciseDto> getExerciseByName(@RequestParam String name){
        return ResponseEntity.ok(exerciseService.getExerciseByName(name));
    }

    @GetMapping("/getAllByMuscleGroup")
    public ResponseEntity<List<ExerciseDto>> getAllExercisesByMuscleGroup(@RequestParam String name){
        return ResponseEntity.ok(exerciseService.getAllExercisesByMuscleGroup(name));
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
