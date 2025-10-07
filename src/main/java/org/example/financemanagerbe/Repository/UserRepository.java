package org.example.financemanagerbe.Repository;
import org.example.financemanagerbe.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    User findBySupabaseUserId(UUID supabaseUserId);
}
