
package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

private final UserService userService;

public UserController(UserService userService) {
this.userService = userService;
}

// Register / create user
@PostMapping
public ResponseEntity<User> createUser(@RequestBody User user) {
User saved = userService.registerUser(user);
return ResponseEntity.ok(saved);
}

// Get by id
@GetMapping("/{id}")
public ResponseEntity<User> getUser(@PathVariable Long id) {
return ResponseEntity.ok(userService.getUser(id));
}

// Get all
@GetMapping
public ResponseEntity<List<User>> getAllUsers() {
return ResponseEntity.ok(userService.getAllUsers());
}
}