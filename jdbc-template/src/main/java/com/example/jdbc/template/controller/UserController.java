package com.example.jdbc.template.controller;

import com.example.jdbc.template.entities.User;
import com.example.jdbc.template.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id) {
        return userRepository.getUserById(id);
    }

    @PostMapping("/users")
    public Integer createUser(@RequestBody User user) {
        return userRepository.createUser(user);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody User user) {
        userRepository.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteUser(id);
    }
}
