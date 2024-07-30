package com.lcwd.user.UserService.controller;

import com.lcwd.user.UserService.entities.User;
import com.lcwd.user.UserService.payload.ApiResponse;
import com.lcwd.user.UserService.services.UserService;
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

    @PostMapping(value = "/createuser")
    public ResponseEntity<User> createUser(@RequestBody User user){
      User u = userService.saveUser(user);
         return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId){
      User user =  userService.getUserById(userId);
        return  ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteById(@PathVariable String userId) {
        userService.deleteById(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }
}
