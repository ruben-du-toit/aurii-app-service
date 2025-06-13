package za.co.aurii.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.aurii.entity.ActivityDetails;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto {

    private UUID id;

    // 🔹 Core universal metadata
    private String title;
    private String description;
    private String category;       // Physical, Cognitive, Creative
    private String type;           // Run, Read, Draw etc.
    private String status;         // Planned, InProgress, Completed

    private String planId;
    private UUID userId;

    private LocalDateTime scheduledDate;
    private LocalDateTime completedDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String timezone;

    private Boolean isManual;
    private Boolean hasHeartRateData;

    // 🔹 Category-specific detail object
    private ActivityDetails details;

}