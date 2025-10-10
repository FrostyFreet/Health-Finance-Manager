package org.example.financemanagerbe.Controller;

import org.example.financemanagerbe.DTO.RequestDto.SetRequest;
import org.example.financemanagerbe.DTO.ResponseDto.WorkoutSetDto;
import org.example.financemanagerbe.Model.WorkoutSet;
import org.example.financemanagerbe.Service.WorkoutSetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sets")
public class WorkoutSetController {

    private final WorkoutSetService workoutSetService;
    public WorkoutSetController(WorkoutSetService workoutSetService){
        this.workoutSetService = workoutSetService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSet(@RequestBody WorkoutSet workoutSet){
        return ResponseEntity.ok(workoutSetService.createSet(workoutSet));
    }
    @GetMapping("/getAllByExerciseId/{id}")
    public ResponseEntity<List<WorkoutSetDto>> getAllSetsByExerciseId(@PathVariable Long id ){
        return ResponseEntity.ok(workoutSetService.getAllSetsByExerciseId(id));
    }

    @PutMapping("/updateSetById")
    public ResponseEntity<WorkoutSetDto> getAllSetsByExerciseId(@RequestParam Long id, @RequestBody SetRequest request ){
        return ResponseEntity.ok(workoutSetService.updateSetById(id,request));
    }
    @DeleteMapping("/deleteSetExerciseById")
    public ResponseEntity<WorkoutSetDto> deleteSetByExerciseId(@RequestParam Long id ){
        return ResponseEntity.ok(workoutSetService.deleteSetByExerciseId(id));
    }
}
