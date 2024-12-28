package com.redisDemo.Redis.annotation;

import com.redisDemo.Redis.service.RedisService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimitAspect {

  private final RedisService rateLimiterService;
  private final HttpServletRequest httpServletRequest;


  @Around("@annotation(rateLimit)")
  public Object rateLimit(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
    // Use client IP as the key for rate limiting
    String clientIp = httpServletRequest.getRemoteAddr();
    String key = joinPoint.getSignature().toShortString() + ":" + clientIp;

    boolean allowed = rateLimiterService.isAllowed(key, rateLimit.limit(), rateLimit.window());

    if (!allowed) {
//      return new ResponseEntity<>("Rate limit exceeded", HttpStatus.TOO_MANY_REQUESTS);
      return "TOO_MANY_REQUESTS";
    }

    return joinPoint.proceed();
  }
}
