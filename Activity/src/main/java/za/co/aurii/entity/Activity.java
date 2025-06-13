package za.co.aurii.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "activities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {

    @Id
    @GeneratedValue
    UUID id;

    private String title;
    private String description;

    private String category;
    private String type;

    @Column(name = "sport_type")
    private String sportType;

    @Column(name = "workout_type")
    private String workoutType;

    private String status;

    @Column(name = "plan_id")
    private UUID planId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "scheduled_date")
    private LocalDateTime scheduledDate;

    @Column(name = "completed_date")
    private LocalDateTime completedDate;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private String timezone;

    @Column(name = "is_manual")
    private Boolean isManual;

    @Column(name = "has_heart_rate_data")
    private Boolean hasHeartRateData;

    @Embedded
    private za.co.aurii.entity.ActivityDetails details;
}
