package com.redisDemo.Redis.service;

import com.redisDemo.Redis.entity.User;
import com.redisDemo.Redis.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Add new user
    public User addUser(User user) {
        return userRepository.save(user);
    }

}
