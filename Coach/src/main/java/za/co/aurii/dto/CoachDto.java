package za.co.aurii.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachDto {
    private Long id;
    private String name;
    private String specialization;
    // Other coach properties
}