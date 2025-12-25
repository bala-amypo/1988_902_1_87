package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Users", description = "User management endpoints")
public class UserController {
private final UserService userService;

public UserController(UserService userService) {
this.userService = userService;
}

@GetMapping("/all")
@Operation(summary = "Get all users")
public ResponseEntity<List<User>> getAllUsers() {
return ResponseEntity.ok(userService.getAllUsers());
}

@GetMapping("/{id}")
@Operation(summary = "Get user by ID")
public ResponseEntity<User> getUserById(@PathVariable Long id) {
return ResponseEntity.ok(userService.getUser(id));
}
}