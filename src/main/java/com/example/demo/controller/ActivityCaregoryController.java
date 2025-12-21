package com.example.demo.controller;

import com.example.demo.entity.ActivityCategory;
import com.example.demo.service.ActivityCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ActivityCategoryController {

private final ActivityCategoryService categoryService;

public ActivityCategoryController(ActivityCategoryService categoryService) {
this.categoryService = categoryService;
}

@PostMapping
public ResponseEntity<ActivityCategory> createCategory(@RequestBody ActivityCategory category) {
ActivityCategory saved = categoryService.createCategory(category);
return ResponseEntity.ok(saved);
}

@GetMapping("/{id}")
public ResponseEntity<ActivityCategory> getCategory(@PathVariable Long id) {
return ResponseEntity.ok(categoryService.getCategory(id));
}

@GetMapping
public ResponseEntity<List<ActivityCategory>> getAllCategories() {
return ResponseEntity.ok(categoryService.getAllCategories());
}
}