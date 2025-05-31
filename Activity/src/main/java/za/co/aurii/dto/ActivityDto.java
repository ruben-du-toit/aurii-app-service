package za.co.aurii.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto {
    private Long id;
    private UUID userId;
    private String type;
    private Integer duration;
    private Double distance;
    private Integer calories;
    private LocalDateTime loggedAt;
    private LocalDateTime createdAt;

}