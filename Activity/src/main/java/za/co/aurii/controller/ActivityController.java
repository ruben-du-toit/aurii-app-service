package za.co.aurii.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.aurii.api.ActivityApi;
import za.co.aurii.dto.ActivityDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/activities")
@CrossOrigin
public class ActivityController {
    // Activity controller methods will be implemented here

    private final ActivityApi activityApi;

    @Autowired
    public ActivityController(ActivityApi activityApi) {
        this.activityApi = activityApi;

    }
    // 🔹 GET all activities
    @GetMapping
    public ResponseEntity<List<ActivityDto>> getAllActivities() {
        List<ActivityDto> activities = activityApi.getAllActivities();
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDto> getActivityById(@PathVariable UUID id) {
        System.out.println("Fetching activity with ID: " + id); // Add this
        return activityApi.getActivityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 PUT update activity by ID
    @PutMapping("/{id}")
    public ResponseEntity<ActivityDto> updateActivity(@PathVariable UUID id, @RequestBody ActivityDto dto) {
        return activityApi.updateActivity(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/createActivity")
    public ResponseEntity<ActivityDto> createActivity(@RequestBody ActivityDto dto) {
        ActivityDto created = activityApi.createActivity(dto);
        return ResponseEntity.ok(created);
    }

}