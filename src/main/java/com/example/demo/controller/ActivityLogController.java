
package com.example.demo.controller;

import com.example.demo.dto.ActivityLogRequest;
import com.example.demo.entity.ActivityLog;
import com.example.demo.service.ActivityLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Activity Logs", description = "Activity logging endpoints")
public class ActivityLogController {
private final ActivityLogService logService;

public ActivityLogController(ActivityLogService logService) {
this.logService = logService;
}

@PostMapping("/{userId}/{typeId}")
@Operation(summary = "Log a new activity")
public ResponseEntity<ActivityLog> logActivity(@PathVariable Long userId, @PathVariable Long typeId,
@RequestBody ActivityLogRequest request) {
ActivityLog log = new ActivityLog();
log.setQuantity(request.getQuantity());
log.setActivityDate(request.getActivityDate());

return ResponseEntity.ok(logService.logActivity(userId, typeId, log));
}

@GetMapping("/user/{userId}")
@Operation(summary = "Get all logs for a user")
public ResponseEntity<List<ActivityLog>> getLogsByUser(@PathVariable Long userId) {
return ResponseEntity.ok(logService.getLogsByUser(userId));
}

@GetMapping("/user/{userId}/range")
@Operation(summary = "Get logs for a user within date range")
public ResponseEntity<List<ActivityLog>> getLogsByUserAndDate(
@PathVariable Long userId,
@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
return ResponseEntity.ok(logService.getLogsByUserAndDate(userId, start, end));
}

@GetMapping("/{id}")
@Operation(summary = "Get activity log by ID")
public ResponseEntity<ActivityLog> getLogById(@PathVariable Long id) {
return ResponseEntity.ok(logService.getLog(id));
}
}

