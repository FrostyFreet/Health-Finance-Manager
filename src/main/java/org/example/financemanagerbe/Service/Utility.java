package org.example.financemanagerbe.Service;

import org.example.financemanagerbe.Model.User;
import org.example.financemanagerbe.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Utility {
    private final UserRepository userRepository;
    public Utility (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getCurrentUser(){
        String currentUUID = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findBySupabaseUserId(UUID.fromString(currentUUID));
    }
}
