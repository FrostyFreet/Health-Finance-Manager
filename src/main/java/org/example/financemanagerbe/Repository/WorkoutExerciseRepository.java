package org.example.financemanagerbe.Repository;

import org.example.financemanagerbe.Model.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
    Optional<WorkoutExercise> findFirstByExercise_IdOrderByCreatedAtDesc(Long exerciseId);
}
