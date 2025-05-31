package za.co.aurii.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "activities")

public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Getter
    @Setter
    @Column(nullable = false)
    private String type;

    @Getter
    @Setter
    @Column(nullable = false)
    private Integer duration;

    @Getter
    @Setter
    @Column
    private Double distance;

    @Getter
    @Setter
    @Column
    private Integer calories;

    @Getter
    @Setter
    @Column(name = "logged_at", nullable = false)
    private LocalDateTime loggedAt;

    @Setter
    @Getter
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Activity() {}

    public Activity(UUID userId, String type, Integer duration, Double distance, Integer calories, LocalDateTime loggedAt, LocalDateTime createdAt) {
        this.userId = userId;
        this.type = type;
        this.duration = duration;
        this.distance = distance;
        this.calories = calories;
        this.loggedAt = loggedAt;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

}
