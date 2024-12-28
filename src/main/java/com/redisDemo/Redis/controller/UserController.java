package com.redisDemo.Redis.controller;

import com.redisDemo.Redis.entity.User;
import com.redisDemo.Redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Add new user
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
