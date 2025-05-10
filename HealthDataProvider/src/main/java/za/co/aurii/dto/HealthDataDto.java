package za.co.aurii.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthDataDto {
    private Long id;
    private String dataType;
    private String value;
    private LocalDateTime timestamp;
    private Long userId;
    // Other health data properties
}