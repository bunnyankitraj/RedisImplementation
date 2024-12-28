package com.redisDemo.Redis.controller;

import com.redisDemo.Redis.annotation.RateLimit;
import com.redisDemo.Redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
public class RedisController {
//  private final RedisService redisService;

  private final RedisService redisService;

  public RedisController(RedisService redisService) {
    this.redisService = redisService;
  }

  @RateLimit(limit = 5, window = 60) // Allow 5 requests per 60 seconds
  @PostMapping("/add")
  public String add(@RequestParam String key, @RequestParam String value) {
    redisService.save(key, value);
    return "OK";
  }

  @RateLimit(limit = 5, window = 60) // Allow 5 requests per 60 seconds
  @PostMapping("/addWithTtl")
  public String addWithTtl(@RequestParam String key, @RequestParam String value, @RequestParam long ttl) {
    redisService.saveWithTtl(key, value, ttl);
    return "OK";
  }

  @RateLimit(limit = 5, window = 60) // Allow 5 requests per 60 seconds
  @PostMapping("/append")
  public String append(@RequestParam String key, @RequestParam String value) {
    redisService.append(key, value);
    return "OK";
  }

  @RateLimit(limit = 2, window = 10) // Allow 5 requests per 60 seconds
  @GetMapping("/get")
  public String get(@RequestParam String key) {
    return redisService.get(key);
  }
}
