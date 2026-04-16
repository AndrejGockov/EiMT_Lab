package com.example.lab_1.controller;

import com.example.lab_1.model.domain.ActivityLog;
import com.example.lab_1.repository.ActivityLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity-logs")
@RequiredArgsConstructor
public class ActivityLogController {

    private final ActivityLogRepository activityLogRepository;

    @GetMapping
    public ResponseEntity<Page<ActivityLog>> getActivityLogs(@PageableDefault(size = 20) Pageable pageable) {
        Page<ActivityLog> logs = activityLogRepository.findAll(pageable);
        return ResponseEntity.ok(logs);
    }
}