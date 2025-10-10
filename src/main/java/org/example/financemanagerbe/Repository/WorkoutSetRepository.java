package org.example.financemanagerbe.Repository;


import org.example.financemanagerbe.Model.WorkoutSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutSetRepository extends JpaRepository<WorkoutSet, Long> {
    List<WorkoutSet> findAllByWorkoutExercise_Id(Long workoutExerciseId);

    Optional<WorkoutSet> findByWorkoutExercise_Id(Long id);
}
