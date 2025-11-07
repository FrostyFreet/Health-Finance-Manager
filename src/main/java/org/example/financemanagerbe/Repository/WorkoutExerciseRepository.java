package org.example.financemanagerbe.Repository;

import org.example.financemanagerbe.DTO.ResponseDto.HighestWeightLastWorkoutProjection;
import org.example.financemanagerbe.Model.WorkoutExercise;
import org.example.financemanagerbe.Model.WorkoutSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
    Optional<WorkoutExercise> findFirstByExercise_IdOrderByCreatedAtDesc(Long exerciseId);


    @Query("""
            SELECT ws
            FROM WorkoutSet ws
            JOIN ws.workoutExercise we
            JOIN we.workout w
            JOIN we.exercise ex
            WHERE ex.id = :exerciseId
              AND w.user.id = :userId
            ORDER BY ws.weight DESC, ws.numberOfReps DESC
            LIMIT 1
        """)
    Optional<WorkoutSet> findHighestWeightEverByExerciseIdAndUserId(
            @Param("exerciseId") Long exerciseId,
            @Param("userId") Long userId
    );
    @Query(value = """
            SELECT
                we.id AS workout_exercise_id,
                ex.id AS exercise_id,
                ex.name AS exercise_name,
                MAX(ws.weight) AS max_weight_last_workout,
                we.created_at AS workout_exercise_created_at,
                MAX(ws.number_of_reps) AS max_number_of_reps,
                w.created_at AS workout_created_at
            FROM workout_exercises we
                     JOIN exercises ex ON ex.id = we.exercise_id
                     JOIN workout_sets ws ON ws.workout_exercise_id = we.id
                     JOIN workouts w ON w.id = we.workout_id
            WHERE ex.name = 'Fekvenyomás' AND user_id = 5
            GROUP BY we.id, ex.id, ex.name, we.created_at, w.id, w.created_at, ws.number_of_reps, ws.weight
            ORDER BY we.created_at DESC, ws.weight DESC, ws.number_of_reps DESC
            LIMIT 1
        """, nativeQuery = true)
    Optional<HighestWeightLastWorkoutProjection> findHighestWeightLastWorkoutByExerciseIdAndUserId(
            @Param("exerciseId") Long exerciseId,
            @Param("userId") Long userId
    );


}
