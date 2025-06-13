package za.co.aurii.mapper;

import org.mapstruct.Mapper;
import za.co.aurii.dto.ActivityDto;
import za.co.aurii.entity.Activity;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    ActivityDto toDto(Activity entity);
    Activity toEntity(ActivityDto dto);

    // Explicit mapping methods:
    default UUID map(Long value) {
        return value == null ? null : UUID.nameUUIDFromBytes(value.toString().getBytes());
    }

    default Long map(UUID value) {
        return null; // or throw, or implement logic to reverse if you need it
    }
}