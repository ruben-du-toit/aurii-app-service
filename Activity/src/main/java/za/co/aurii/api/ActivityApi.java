package za.co.aurii.api;

import za.co.aurii.dto.ActivityDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface defining the Activity API contract
 */
public interface ActivityApi {
    // API contract methods for Activity module

    List<ActivityDto> getAllActivities();

    Optional<ActivityDto> getActivityById(UUID id);
    Optional<ActivityDto> updateActivity(UUID id, ActivityDto dto);

    ActivityDto createActivity(ActivityDto dto);
    List<ActivityDto> findByUserId(UUID userId);
}