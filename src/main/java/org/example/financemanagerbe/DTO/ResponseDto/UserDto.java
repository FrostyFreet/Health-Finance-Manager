package org.example.financemanagerbe.DTO.ResponseDto;

import lombok.Getter;
import lombok.Setter;
import org.example.financemanagerbe.Model.ActivityLevel;
import org.example.financemanagerbe.Model.Gender;
import org.example.financemanagerbe.Model.User;

@Getter
@Setter
public class UserDto {
    private String lastName;
    private String firstName;
    private Integer age;
    private Double weight;
    private Integer height;
    private String email;
    private Gender gender;
    private Double tdee;
    private ActivityLevel activityLevel;

    public UserDto(User user){
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.age = user.getAge();
        this.weight = user.getWeight();
        this.height = user.getHeight();
        this.email = user.getEmail();
        this.tdee = user.getTdee();
        this.gender = user.getGender();
        this.activityLevel = user.getActivityLevel();
    }

}
