package za.co.aurii.api;

import za.co.aurii.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface defining the User API contract
 */
public interface UserApi {
    // API contract methods for User module

    List<UserDto> getAllUsers();
    Optional<UserDto> getUserById(UUID id);
    Optional<UserDto> getUserByEmail(String email);
    UserDto createUser(UserDto dto);
    Optional<UserDto> updateUser(UUID id, UserDto dto);
}