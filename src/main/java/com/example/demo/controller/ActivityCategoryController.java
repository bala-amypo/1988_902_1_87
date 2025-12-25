
package com.example.demo.controller;

import com.example.demo.dto.CategoryRequest;
import com.example.demo.entity.ActivityCategory;
import com.example.demo.service.ActivityCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Activity Categories", description = "Activity category management endpoints")
public class ActivityCategoryController {
private final ActivityCategoryService categoryService;

public ActivityCategoryController(ActivityCategoryService categoryService) {
this.categoryService = categoryService;
}

@PostMapping
@Operation(summary = "Create a new activity category")
public ResponseEntity<ActivityCategory> createCategory(@RequestBody CategoryRequest request) {
ActivityCategory category = new ActivityCategory();
category.setCategoryName(request.getName());
category.setDescription(request.getDescription());
return ResponseEntity.ok(categoryService.createCategory(category));
}

@GetMapping
@Operation(summary = "Get all activity categories")
public ResponseEntity<List<ActivityCategory>> getAllCategories() {
return ResponseEntity.ok(categoryService.getAllCategories());
}

@GetMapping("/{id}")
@Operation(summary = "Get activity category by ID")
public ResponseEntity<ActivityCategory> getCategoryById(@PathVariable Long id) {
return ResponseEntity.ok(categoryService.getCategory(id));
}
}
