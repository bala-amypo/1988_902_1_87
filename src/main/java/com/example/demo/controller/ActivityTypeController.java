package com.example.demo.controller;

 

import com.example.demo.entity.ActivityType;

import com.example.demo.service.ActivityTypeService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

 

import java.util.List;

 

@RestController

@RequestMapping("/api/types")

public class ActivityTypeController {

   

    private final ActivityTypeService typeService;

   

    public ActivityTypeController(ActivityTypeService typeService) {

        this.typeService = typeService;

    }

   

    @PostMapping("/category/{categoryId}")

    public ResponseEntity<ActivityType> createType(@PathVariable Long categoryId, @RequestBody ActivityType type) {

        ActivityType savedType = typeService.createType(categoryId, type);

        return ResponseEntity.ok(savedType);

    }

   

    @GetMapping("/{id}")

    public ResponseEntity<ActivityType> getType(@PathVariable Long id) {

        ActivityType type = typeService.getType(id);

        return ResponseEntity.ok(type);

    }

   

    @GetMapping("/category/{categoryId}")

    public ResponseEntity<List<ActivityType>> getTypesByCategory(@PathVariable Long categoryId) {

        List<ActivityType> types = typeService.getTypesByCategory(categoryId);

        return ResponseEntity.ok(types);

    }

}