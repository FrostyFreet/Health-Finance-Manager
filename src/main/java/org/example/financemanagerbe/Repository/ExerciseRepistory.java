package org.example.financemanagerbe.Repository;

import org.example.financemanagerbe.Model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepistory extends JpaRepository<Exercise, Long> {
    Exercise findByName(String name);
    List<Exercise> findAllByMuscleGroup(String muscleGroup);

    Exercise findByNameContainingIgnoreCase(String name);
}
