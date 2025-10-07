package org.example.financemanagerbe.Service;

import org.example.financemanagerbe.Model.User;
import org.example.financemanagerbe.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<String> register(User user) {
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        user.setSupabaseUserId(UUID.fromString(currentUserId));
        userRepository.save(user);

        return ResponseEntity.ok("Registered!");
    }

    public ResponseEntity<String> updateUser(Long id, User user) {
        User foundUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No user found!"));

        if (user.getId() != null || user.getSupabaseUserId() != null || user.getEmail() != null) throw new RuntimeException("Invalid update!");

        if (user.getFirstName() != null) foundUser.setFirstName(user.getFirstName());
        if (user.getLastName() != null) foundUser.setLastName(user.getLastName());
        if (user.getAge() != null) foundUser.setAge(user.getAge());
        if (user.getWeight() != null) foundUser.setWeight(user.getWeight());
        if (user.getHeight() != null) foundUser.setHeight(user.getHeight());
        if (user.getTdee() != null) foundUser.setTdee(user.getTdee());
        if (user.getActivityLevel() != null) foundUser.setActivityLevel(user.getActivityLevel());
        userRepository.save(foundUser);

        return ResponseEntity.ok("Updated!");
    }
}
