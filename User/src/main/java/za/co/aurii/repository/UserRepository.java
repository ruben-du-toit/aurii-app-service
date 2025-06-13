package za.co.aurii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.aurii.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findById(UUID id);
    Optional<UserEntity> findByName(String name);
    Optional<UserEntity> findByEmail(String email);

}
