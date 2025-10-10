package org.example.financemanagerbe.Controller;
import org.example.financemanagerbe.DTO.RequestDto.RegisterRequest;
import org.example.financemanagerbe.DTO.RequestDto.UserRequest;
import org.example.financemanagerbe.DTO.ResponseDto.UserDto;
import org.example.financemanagerbe.Model.User;
import org.example.financemanagerbe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController (UserService userService){
        this.userService =userService;

    }

    @GetMapping("/getUserData")
    public ResponseEntity<UserDto> getUserData(){
       return userService.getUserData();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        return userService.register(request);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserRequest> updateUser(@PathVariable Long id, @RequestBody UserRequest user){
        return userService.updateUser(id,user);
    }
    @PutMapping("/updateCurrentUser")
    public ResponseEntity<UserRequest> updateCurrentUser(@RequestBody UserRequest user){
        return userService.updateCurrentUser(user);
    }







}
