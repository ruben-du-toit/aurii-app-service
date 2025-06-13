package za.co.aurii.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.aurii.api.ActivityApi;
import za.co.aurii.dto.ActivityDto;
import za.co.aurii.entity.Activity;
import za.co.aurii.mapper.ActivityMapper;
import za.co.aurii.repository.ActivityRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityApi {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    @Override
    public List<ActivityDto> getAllActivities() {
        return activityRepository.findAll()
                .stream()
                .map(activityMapper::toDto)
                .toList();
    }

    @Override
    public Optional<ActivityDto> getActivityById(UUID id) {
        return activityRepository.findById(id)
                .map(activityMapper::toDto);
    }

    @Override
    public Optional<ActivityDto> updateActivity(UUID id, ActivityDto dto) {
        return activityRepository.findById(id)
                .map(existing -> {
                    Activity updated = activityMapper.toEntity(dto);
                    updated.setId(existing.getId()); // preserve ID
                    Activity saved = activityRepository.save(updated);
                    return activityMapper.toDto(saved);
                });
    }

    @Override
    public ActivityDto createActivity(ActivityDto dto) {
        Activity saved = activityRepository.save(activityMapper.toEntity(dto));
        return activityMapper.toDto(saved);
    }
}
