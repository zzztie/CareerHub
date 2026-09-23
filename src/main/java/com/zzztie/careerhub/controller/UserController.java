package com.zzztie.careerhub.controller;

import com.zzztie.careerhub.domain.User;
import com.zzztie.careerhub.dto.CreateUserRequest;
import com.zzztie.careerhub.dto.UpdateUserRequest;
import com.zzztie.careerhub.dto.UserResponse;
import com.zzztie.careerhub.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users/count")
    public int countUsers(){
        return userService.getAllUsers().size();
    }

    @PostMapping("/users")
    public UserResponse createUser(@RequestBody CreateUserRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        User savedUser = userService.createUser(user);
        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getStatus()
        );
    }
    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return new UserResponse(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getStatus());
    }
    @GetMapping("/users")
    public List<UserResponse> getAllUsers(){
        List<User> users = userService.getAllUsers();
        List<UserResponse> userResponses = users.stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getStatus()
                ))
                .toList();
        return userResponses;
    }
    @PutMapping("/users/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request
            ){
        User user = userService.getUserById(id);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        User updateUser = userService.updateUser(user);
        return new UserResponse(
                updateUser.getId(),
                updateUser.getUsername(),
                updateUser.getEmail(),
                updateUser.getStatus()
        );
    }
    @DeleteMapping("/users/{id}")
    public boolean deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
