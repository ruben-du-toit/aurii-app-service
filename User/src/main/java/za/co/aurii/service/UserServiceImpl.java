package za.co.aurii.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.aurii.api.UserApi;
import za.co.aurii.dto.UserDto;
import za.co.aurii.entity.UserEntity;
import za.co.aurii.mapper.UserMapper;
import za.co.aurii.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserApi {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public Optional<UserDto> getUserById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    @Override
    public Optional<UserDto> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDto);
    }

    @Override
    public UserDto createUser(UserDto dto) {
        UserEntity saved = userRepository.save(userMapper.toEntity(dto));
        return userMapper.toDto(saved);
    }

    @Override
    public Optional<UserDto> updateUser(UUID id, UserDto dto) {
        return userRepository.findById(id)
                .map(existing -> {
                    UserEntity updated = userMapper.toEntity(dto);
                    updated.setId(existing.getId()); // preserve ID
                    return userMapper.toDto(userRepository.save(updated));
                });
    }
}