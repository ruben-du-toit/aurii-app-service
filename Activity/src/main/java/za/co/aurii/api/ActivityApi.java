package za.co.aurii.api;

import za.co.aurii.dto.ActivityDto;

import java.util.List;
import java.util.Optional;

/**
 * Interface defining the Activity API contract
 */
public interface ActivityApi {
    // API contract methods for Activity module

    List<ActivityDto> getAllActivities();

    Optional<ActivityDto> getActivityById(Long id);

    Optional<ActivityDto> updateActivity(Long id, ActivityDto dto);

    ActivityDto createActivity(ActivityDto dto);

}