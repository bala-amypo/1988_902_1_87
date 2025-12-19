package com.example.demo.controller;

 

import com.example.demo.entity.User;

import com.example.demo.service.UserService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

 

@RestController

@RequestMapping("/auth")

public class AuthController {

   

    private final UserService userService;

   

    public AuthController(UserService userService) {

        this.userService = userService;

    }

   

    @PostMapping("/register")

    public ResponseEntity<User> register(@RequestBody User user) {

        User savedUser = userService.registerUser(user);

        return ResponseEntity.ok(savedUser);

    }

   

    @PostMapping("/login")

    public ResponseEntity<User> login(@RequestBody User loginUser) {

        User user = userService.loginUser(loginUser.getEmail(), loginUser.getPassword());

        return ResponseEntity.ok(user);

    }

}