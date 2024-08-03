package com.lcwd.user.UserService.controller;

import com.lcwd.user.UserService.entities.User;
import com.lcwd.user.UserService.payload.ApiResponse;
import com.lcwd.user.UserService.services.UserService;
import com.lcwd.user.UserService.services.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserService.class);
    int retryCount = 1;


    @PostMapping(value = "/createuser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User u = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        logger.info("Retry Count : {}",retryCount);
        retryCount++;
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteById(@PathVariable String userId) {
        userService.deleteById(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }

    /**
     * creating ratingHotelFallback() for circuit breaker
     */
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
        logger.info("Fallback method executed because service is down " + ex.getMessage());

        User user = User.builder()
                .email("Dummy@gmail.com")
                .name("Dummy")
                .about("This is dummy user created when service is down ")
                .userId("12234")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
