package za.co.aurii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.aurii.entity.Activity;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, UUID> {

    List<Activity> findByUserId(UUID userId);
    List<Activity> findByUserIdAndType(UUID userId, String type);

}
