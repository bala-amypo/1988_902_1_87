package com.example.demo.controller;

import com.example.demo.dto.EmissionFactorRequest;
import com.example.demo.entity.EmissionFactor;
import com.example.demo.service.EmissionFactorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factors")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Emission Factors", description = "Emission factor management endpoints")
public class EmissionFactorController {
private final EmissionFactorService factorService;

public EmissionFactorController(EmissionFactorService factorService) {
this.factorService = factorService;
}

@PostMapping("/{activityTypeId}")
@Operation(summary = "Create a new emission factor")
public ResponseEntity<EmissionFactor> createFactor(@PathVariable Long activityTypeId,
@RequestBody EmissionFactorRequest request) {
EmissionFactor factor = new EmissionFactor();
factor.setFactorValue(request.getFactorValue());
factor.setUnit(request.getUnit());
return ResponseEntity.ok(factorService.createFactor(activityTypeId, factor));
}

@GetMapping("/{id}")
@Operation(summary = "Get emission factor by ID")
public ResponseEntity<EmissionFactor> getFactorById(@PathVariable Long id) {
return ResponseEntity.ok(factorService.getFactor(id));
}

@GetMapping("/type/{activityTypeId}")
@Operation(summary = "Get emission factor by activity type")
public ResponseEntity<EmissionFactor> getFactorByType(@PathVariable Long activityTypeId) {
return ResponseEntity.ok(factorService.getFactorByType(activityTypeId));
}

@GetMapping
@Operation(summary = "Get all emission factors")
public ResponseEntity<List<EmissionFactor>> getAllFactors() {
return ResponseEntity.ok(factorService.getAllFactors());
}
}