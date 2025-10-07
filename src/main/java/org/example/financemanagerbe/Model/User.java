package org.example.financemanagerbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "supabase_user_id", unique = true, nullable = false)
    private UUID supabaseUserId;
    private String lastName;
    private String firstName;
    private Integer age;
    private Double weight;
    private Integer height;
    @Column(nullable = false)
    private String email;
    private Integer tdee;
    private ActivityLevel activityLevel;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Workouts> workouts;
}
