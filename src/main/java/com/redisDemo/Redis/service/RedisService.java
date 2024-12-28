package com.redisDemo.Redis.service;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

  private final RedisTemplate<String, Object> redisTemplate;

  public boolean isAllowed(String key, int limit, int windowInSeconds) {
    String redisKey = "rate:" + key;
    Long currentCount = redisTemplate.opsForValue().increment(redisKey);

    if (currentCount == 1) {
      // Set TTL for the key on the first request
      redisTemplate.expire(redisKey, windowInSeconds, TimeUnit.SECONDS);
    }

    return currentCount <= limit;
  }


  public void save(String key, String value) {
    redisTemplate.opsForValue().set(key, value);
  }

  public String get(String key) {
    return (String) redisTemplate.opsForValue().get(key);
  }

  public void saveWithTtl(String key, String value, long ttl) {
    redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
  }

  public void append(String key, String value) {
    redisTemplate.opsForValue().append(key, value);
  }

//  public void delete(String key) {
//    redisTemplate.delete(key);
//  }


}
