package za.co.aurii.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto {
    private Long id;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    // Other activity properties
}