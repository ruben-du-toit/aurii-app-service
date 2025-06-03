package za.co.aurii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.aurii.api.ActivityApi;
import za.co.aurii.dto.ActivityDto;
import za.co.aurii.entity.Activity;
import za.co.aurii.repository.ActivityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityApi {
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    private ActivityDto toDto(Activity activity) {
        return new ActivityDto(
                activity.getId(),
                activity.getUserId(),
                activity.getType(),
                activity.getDuration(),
                activity.getDistance(),
                activity.getCalories(),
                activity.getLoggedAt(),
                activity.getCreatedAt()
        );
    }

    private Activity toEntity(ActivityDto dto) {
        return new Activity(
                dto.getUserId(),
                dto.getType(),
                dto.getDuration(),
                dto.getDistance(),
                dto.getCalories(),
                dto.getLoggedAt(),
                dto.getCreatedAt()
        );
    }

    @Override
    public List<ActivityDto> getAllActivities() {
    return activityRepository.findAll()
            .stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<ActivityDto> getActivityById(Long id) {
        System.out.println("🔍 Service called with ID: " + id);
        Optional<Activity> activity = activityRepository.findById(id);
        System.out.println("🔎 Activity found: " + activity.isPresent());
        return activity.map(this::toDto);
    }
    @Override
    public Optional<ActivityDto> updateActivity(Long id, ActivityDto dto) {
        return Optional.empty();
    }

    @Override
    public ActivityDto createActivity(ActivityDto dto) {
        // Convert DTO to entity
        Activity activity = new Activity();
        activity.setUserId(dto.getUserId());
        activity.setType(dto.getType());
        activity.setDuration(dto.getDuration());
        activity.setDistance(dto.getDistance());
        activity.setCalories(dto.getCalories());
        activity.setLoggedAt(dto.getLoggedAt());
        activity.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : java.time.LocalDateTime.now());

        // Save to database
        Activity savedActivity = activityRepository.save(activity);

        // Convert saved entity back to DTO and return
        return toDto(savedActivity);
    }

}