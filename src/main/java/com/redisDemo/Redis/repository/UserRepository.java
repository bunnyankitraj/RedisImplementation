package com.redisDemo.Redis.repository;

import com.redisDemo.Redis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
