package com.example.demo.controller;

import com.example.demo.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

private final UserRepository userRepository;
private final ActivityCategoryRepository categoryRepository;
private final ActivityTypeRepository typeRepository;
private final ActivityLogRepository logRepository;
private final EmissionFactorRepository factorRepository;

public AdminController(UserRepository userRepository,
ActivityCategoryRepository categoryRepository,
ActivityTypeRepository typeRepository,
ActivityLogRepository logRepository,
EmissionFactorRepository factorRepository) {
this.userRepository = userRepository;
this.categoryRepository = categoryRepository;
this.typeRepository = typeRepository;
this.logRepository = logRepository;
this.factorRepository = factorRepository;
}

@DeleteMapping("/clear-all")
public ResponseEntity<String> clearAllData() {
logRepository.deleteAll();
factorRepository.deleteAll();
typeRepository.deleteAll();
categoryRepository.deleteAll();
userRepository.deleteAll();
return ResponseEntity.ok("All data cleared successfully");
}

@DeleteMapping("/clear-logs")
public ResponseEntity<String> clearLogs() {
logRepository.deleteAll();
return ResponseEntity.ok("Activity logs cleared");
}

@DeleteMapping("/clear-categories")
public ResponseEntity<String> clearCategories() {
logRepository.deleteAll();
factorRepository.deleteAll();
typeRepository.deleteAll();
categoryRepository.deleteAll();
return ResponseEntity.ok("Categories and related data cleared");
}

@DeleteMapping("/drop-database")
public ResponseEntity<String> dropDatabase() {
logRepository.deleteAll();
factorRepository.deleteAll();
typeRepository.deleteAll();
categoryRepository.deleteAll();
userRepository.deleteAll();
return ResponseEntity.ok("Database dropped successfully");
}
}
