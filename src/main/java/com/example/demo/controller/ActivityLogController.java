
package com.example.demo.controller;

import com.example.demo.entity.ActivityLog;
import com.example.demo.service.ActivityLogService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class ActivityLogController {

private final ActivityLogService logService;

public ActivityLogController(ActivityLogService logService) {
this.logService = logService;
}

// Log activity for a user and type
@PostMapping("/{userId}/{typeId}")
public ResponseEntity<ActivityLog> logActivity(@PathVariable Long userId,
@PathVariable Long typeId,
@RequestBody ActivityLog log) {
ActivityLog saved = logService.logActivity(userId, typeId, log);
return ResponseEntity.ok(saved);
}

// Get log by id
@GetMapping("/{id}")
public ResponseEntity<ActivityLog> getLog(@PathVariable Long id) {
return ResponseEntity.ok(logService.getLog(id));
}

// All logs for user
@GetMapping("/user/{userId}")
public ResponseEntity<List<ActivityLog>> getLogsByUser(@PathVariable Long userId) {
return ResponseEntity.ok(logService.getLogsByUser(userId));
}

// Logs by user and date range
@GetMapping("/user/{userId}/range")
public ResponseEntity<List<ActivityLog>> getLogsByUserAndDate(
@PathVariable Long userId,
@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

return ResponseEntity.ok(logService.getLogsByUserAndDate(userId, start, end));
}
}