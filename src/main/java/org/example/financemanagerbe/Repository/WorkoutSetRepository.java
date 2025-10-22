package org.example.financemanagerbe.Repository;


import org.example.financemanagerbe.Model.WorkoutSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkoutSetRepository extends JpaRepository<WorkoutSet, Long> {
    List<WorkoutSet> findAllByWorkoutExercise_Id(Long workoutExerciseId);

    @Query("SELECT ws FROM WorkoutSet ws " +
            "JOIN ws.workoutExercise we " +
            "JOIN we.workout w " +
            "JOIN we.exercise ex " +
            "WHERE ex.id = :exerciseId " +
            "AND w.user.id = :userId " +
            "AND w.createdAt BETWEEN :startDate AND :endDate " +
            "ORDER BY w.createdAt ASC")

    List<WorkoutSet> findByExerciseAndDateRange(
            @Param("userId") Long userId,
            @Param("exerciseId") Long exerciseId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );}
