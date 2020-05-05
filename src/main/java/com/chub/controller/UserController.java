package com.chub.controller;

import com.chub.mode.UserEntity;
import com.chub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<UserEntity> getUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @PostMapping("/user")
    void addUser(@RequestBody UserEntity user) {
        userRepository.save(user);
    }
}
