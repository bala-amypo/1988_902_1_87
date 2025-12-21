
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

// Create a type under a category
@PostMapping("/category/{categoryId}")
public ResponseEntity<ActivityType> createType(@PathVariable Long categoryId,
@RequestBody ActivityType type) {
ActivityType saved = typeService.createType(categoryId, type);
return ResponseEntity.ok(saved);
}

// Get type by id
@GetMapping("/{id}")
public ResponseEntity<ActivityType> getType(@PathVariable Long id) {
return ResponseEntity.ok(typeService.getType(id));
}

// Get all types for a category
@GetMapping("/category/{categoryId}")
public ResponseEntity<List<ActivityType>> getTypesByCategory(@PathVariable Long categoryId) {
return ResponseEntity.ok(typeService.getTypesByCategory(categoryId));
}
}
