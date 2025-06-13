package za.co.aurii.mapper;

import org.springframework.stereotype.Component;
import za.co.aurii.dto.UserDto;
import za.co.aurii.entity.UserEntity;

@Component
public class UserMapper {

    public UserDto toDto(UserEntity user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }

    public UserEntity toEntity(UserDto dto) {
        return UserEntity.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .name(dto.getName())
                .role(dto.getRole())
                .build();
    }
}
