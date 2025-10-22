package org.example.financemanagerbe.Repository;

import org.example.financemanagerbe.Model.User;
import org.example.financemanagerbe.Model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout,Long> {
    Optional<Workout> findByTitle(String title);
    List<Workout> findAllByUser(User user);

    List<Workout> findAllByUserOrderByCreatedAtDesc(User currentUser);
    Workout findByUserOrderByCreatedAtDesc(User currentUser);

    List<Workout> findAllByCreatedAtBetween(LocalDateTime createdAtAfter, LocalDateTime createdAtBefore);
}
