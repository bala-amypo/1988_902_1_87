
package com.example.demo.controller;

import com.example.demo.entity.EmissionFactor;
import com.example.demo.service.EmissionFactorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factors")
public class EmissionFactorController {

private final EmissionFactorService factorService;

public EmissionFactorController(EmissionFactorService factorService) {
this.factorService = factorService;
}

// Create factor for an activity type
@PostMapping("/type/{activityTypeId}")
public ResponseEntity<EmissionFactor> createFactor(@PathVariable Long activityTypeId,
@RequestBody EmissionFactor factor) {
try {
EmissionFactor saved = factorService.createFactor(activityTypeId, factor);
return ResponseEntity.ok(saved);
} catch (Exception e) {
throw e; // Let GlobalExceptionHandler handle it
}
}

// Get factor by id
@GetMapping("/{id}")
public ResponseEntity<EmissionFactor> getFactor(@PathVariable Long id) {
return ResponseEntity.ok(factorService.getFactor(id));
}

// Get factor by activity type id
@GetMapping("/type/{activityTypeId}")
public ResponseEntity<EmissionFactor> getFactorByType(@PathVariable Long activityTypeId) {
return ResponseEntity.ok(factorService.getFactorByType(activityTypeId));
}

// Get all
@GetMapping
public ResponseEntity<List<EmissionFactor>> getAllFactors() {
return ResponseEntity.ok(factorService.getAllFactors());
}
}

