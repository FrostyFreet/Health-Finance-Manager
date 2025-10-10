package org.example.financemanagerbe.DTO.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.financemanagerbe.Model.ActivityLevel;
import org.example.financemanagerbe.Model.Gender;
import org.example.financemanagerbe.Model.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String lastName;
    private String firstName;
    private Integer age;
    private Double weight;
    private Integer height;
    private Double tdee;
    private Gender gender;
    private ActivityLevel activityLevel;

    public UserRequest(User user){
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.age = user.getAge();
        this.weight = user.getWeight();
        this.height = user.getHeight();
        this.tdee = user.getTdee();
        this.gender = user.getGender();
        this.activityLevel = user.getActivityLevel();
    }
}
