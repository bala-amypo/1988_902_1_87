package com.example.demo.controller;

import com.example.demo.dto.ActivityTypeRequest;
import com.example.demo.entity.ActivityType;
import com.example.demo.service.ActivityTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Activity Types", description = "Activity type management endpoints")
public class ActivityTypeController {
private final ActivityTypeService typeService;

public ActivityTypeController(ActivityTypeService typeService) {
this.typeService = typeService;
}

@PostMapping("/category/{categoryId}")
@Operation(summary = "Create a new activity type under a category")
public ResponseEntity<ActivityType> createType(@PathVariable Long categoryId, @RequestBody ActivityTypeRequest request) {
ActivityType type = new ActivityType();
type.setTypeName(request.getName());
type.setUnit(request.getUnit());
return ResponseEntity.ok(typeService.createType(categoryId, type));
}

@GetMapping("/category/{categoryId}")
@Operation(summary = "Get activity types by category")
public ResponseEntity<List<ActivityType>> getTypesByCategory(@PathVariable Long categoryId) {
return ResponseEntity.ok(typeService.getTypesByCategory(categoryId));
}

@GetMapping("/{id}")
@Operation(summary = "Get activity type by ID")
public ResponseEntity<ActivityType> getTypeById(@PathVariable Long id) {
return ResponseEntity.ok(typeService.getType(id));
}
}
