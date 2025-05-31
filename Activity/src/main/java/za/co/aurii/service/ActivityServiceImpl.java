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
        return Optional.empty();
    }

    @Override
    public Optional<ActivityDto> updateActivity(Long id, ActivityDto dto) {
        return Optional.empty();
    }

}