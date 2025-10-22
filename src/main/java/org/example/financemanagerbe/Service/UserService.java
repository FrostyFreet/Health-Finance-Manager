package org.example.financemanagerbe.Service;

import org.example.financemanagerbe.DTO.RequestDto.RegisterRequest;
import org.example.financemanagerbe.DTO.RequestDto.UserRequest;
import org.example.financemanagerbe.DTO.ResponseDto.UserDto;
import org.example.financemanagerbe.Model.ActivityLevel;
import org.example.financemanagerbe.Model.Gender;
import org.example.financemanagerbe.Model.User;
import org.example.financemanagerbe.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Utility utility;

    @Autowired
    public UserService (UserRepository userRepository,Utility utility){
        this.userRepository =userRepository;
        this.utility = utility;
    }

    public ResponseEntity<UserDto> getUserData(){
        User foundUser = utility.getCurrentUser();
        return ResponseEntity.ok(new UserDto(foundUser));
    }

    public ResponseEntity<?> register(RegisterRequest request) {
        if (request == null) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Invalid request"));
        }

        try {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());

            if (request.getSupabaseUserId() != null && !request.getSupabaseUserId().isBlank()) {
                UUID uuid = UUID.fromString(request.getSupabaseUserId());
                user.setSupabaseUserId(uuid);
            }

            User saved = userRepository.save(user);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of(
                            "message", "User registered successfully",
                            "userId", saved.getId(),
                            "email", saved.getEmail()
                    ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Invalid supabaseUserId"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to register user"));
        }
    }

    public ResponseEntity<UserRequest> updateUser(Long id, UserRequest user) {
        User foundUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No user found!"));

        if (user.getFirstName() != null) foundUser.setFirstName(user.getFirstName());
        if (user.getLastName() != null) foundUser.setLastName(user.getLastName());
        if (user.getAge() != null) foundUser.setAge(user.getAge());
        if (user.getWeight() != null) foundUser.setWeight(user.getWeight());
        if (user.getHeight() != null) foundUser.setHeight(user.getHeight());
        if (user.getTdee() != null) foundUser.setTdee(user.getTdee());
        if (user.getActivityLevel() != null) foundUser.setActivityLevel(user.getActivityLevel());
        if (user.getGender() != null) foundUser.setGender(user.getGender());

        userRepository.save(foundUser);
        UserRequest savedUser = new UserRequest(foundUser);

        return ResponseEntity.ok(savedUser);
    }
    public ResponseEntity<UserRequest> updateCurrentUser(UserRequest user) {
        User foundUser = utility.getCurrentUser();

        if (user.getFirstName() != null) foundUser.setFirstName(user.getFirstName());
        if (user.getLastName() != null) foundUser.setLastName(user.getLastName());
        if (user.getAge() != null) foundUser.setAge(user.getAge());
        if (user.getWeight() != null) foundUser.setWeight(user.getWeight());
        if (user.getHeight() != null) foundUser.setHeight(user.getHeight());
        if (user.getTdee() != null) foundUser.setTdee(user.getTdee());
        if (user.getActivityLevel() != null) foundUser.setActivityLevel(user.getActivityLevel());
        if (user.getGender() != null) foundUser.setGender(user.getGender());

        userRepository.save(foundUser);
        if (foundUser.getAge() != null && foundUser.getWeight() != null && foundUser.getHeight() != null
                && foundUser.getActivityLevel() != null && foundUser.getGender() != null) {
            double BMR;
            double tdee = 0.0;
            if (foundUser.getGender().equals(Gender.MALE)) {
                BMR = (10 * foundUser.getWeight()) + (6.25 * foundUser.getHeight()) - (5 * foundUser.getAge()) + 5;
            } else {
                BMR = (10 * foundUser.getWeight()) + (6.25 * foundUser.getHeight()) - (5 * foundUser.getAge()) - 161;
            }
            switch (foundUser.getActivityLevel()) {
                case ActivityLevel.Lightly_Active:
                    tdee = BMR * 1.375;
                    break;
                case Moderately_Active:
                    tdee = BMR * 1.55;
                    break;
                case Sedentary:
                    tdee = BMR * 1.2;
                    break;
                case Very_Active:
                    tdee = BMR * 1.725;
                    break;
            }
            foundUser.setTdee(tdee);
            userRepository.save(foundUser);
        }
        UserRequest savedUser = new UserRequest(foundUser);

        return ResponseEntity.ok(savedUser);
    }
}
