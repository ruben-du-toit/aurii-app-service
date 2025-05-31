package za.co.aurii.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.aurii.api.ActivityApi;
import za.co.aurii.dto.ActivityDto;

import java.util.List;

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

    // 🔹 GET single activity by ID
    @GetMapping("/{id}")
    public ResponseEntity<ActivityDto> getActivityById(@PathVariable Long id) {
        return activityApi.getActivityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 PUT update activity by ID
    @PutMapping("/{id}")
    public ResponseEntity<ActivityDto> updateActivity(@PathVariable Long id, @RequestBody ActivityDto dto) {
        return activityApi.updateActivity(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}