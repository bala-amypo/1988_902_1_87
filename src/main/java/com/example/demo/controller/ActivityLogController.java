package com.example.demo.controller;

 

import com.example.demo.dto.ActivityLogRequest;

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

   

    @PostMapping("/{userId}/{typeId}")

    public ResponseEntity<ActivityLog> logActivity(@PathVariable Long userId, @PathVariable Long typeId,

                                                  @RequestBody ActivityLogRequest request) {

        ActivityLog log = new ActivityLog();

        log.setQuantity(request.getQuantity());

        log.setActivityDate(request.getActivityDate());

       

        ActivityLog savedLog = logService.logActivity(userId, typeId, log);

        return ResponseEntity.ok(savedLog);

    }

   

    @GetMapping("/user/{userId}")

    public ResponseEntity<List<ActivityLog>> getLogsByUser(@PathVariable Long userId) {

        List<ActivityLog> logs = logService.getLogsByUser(userId);

        return ResponseEntity.ok(logs);

    }

   

    @GetMapping("/user/{userId}/range")

    public ResponseEntity<List<ActivityLog>> getLogsByUserAndDate(

            @PathVariable Long userId,

            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,

            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        List<ActivityLog> logs = logService.getLogsByUserAndDate(userId, start, end);

        return ResponseEntity.ok(logs);

    }

   

    @GetMapping("/{id}")

    public ResponseEntity<ActivityLog> getLog(@PathVariable Long id) {

        ActivityLog log = logService.getLog(id);

        return ResponseEntity.ok(log);

    }

}