package com.example.demo.controller;

import com.example.demo.entity.ActivityCategory;
import com.example.demo.service.ActivityCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Activity Categories", description = "Activity category management operations")
public class ActivityCategoryController {

private final ActivityCategoryService categoryService;

public ActivityCategoryController(ActivityCategoryService categoryService) {
this.categoryService = categoryService;
}

@PostMapping
public ResponseEntity<ActivityCategory> createCategory(@RequestBody ActivityCategory category) {
ActivityCategory savedCategory = categoryService.createCategory(category);
return ResponseEntity.ok(savedCategory);
}

@GetMapping("/{id}")
public ResponseEntity<ActivityCategory> getCategory(@PathVariable Long id) {
ActivityCategory category = categoryService.getCategory(id);
return ResponseEntity.ok(category);
}

@GetMapping
public ResponseEntity<List<ActivityCategory>> getAllCategories() {
List<ActivityCategory> categories = categoryService.getAllCategories();
return ResponseEntity.ok(categories);
}
}